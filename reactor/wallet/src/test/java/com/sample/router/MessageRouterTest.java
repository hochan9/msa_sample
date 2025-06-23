package com.sample.router;/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sample.handler.MessageHandler;
import com.sample.message.dto.MessageDto;
import com.sample.message.publisher.MessagePublisher;
import com.sample.subscriber.MessageSubscriber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>클래스 설명. </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import({MessageRouter.class, MessageHandler.class})
class MessageRouterTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockitoBean
  private MessageSubscriber subscriber;

  @MockitoBean
  private MessagePublisher publisher;


  @MockitoBean
  private R2dbcMappingContext r2dbcMappingContext;

  @Test
  void GET_streaming_messages_성공() {
    // given
    MessageDto message1 = new MessageDto("id", "hello");
    MessageDto message2 = new MessageDto("id", "world");

    // 하나의 Sink를 사용하여 동일한 데이터 흐름을 확인
    Sinks.Many<MessageDto> sink = Sinks.many().replay().all();
    when(subscriber.getSink(anyString())).thenReturn(sink);

    // then
    webTestClient.get()
        .uri("/streaming-messages/{userId}", message1.getId())
        .accept(MediaType.TEXT_EVENT_STREAM)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM)
        .returnResult(MessageDto.class)
        .getResponseBody()
        .as(StepVerifier::create)
        .expectNextMatches(response -> response.getMessage().equals(message1.getMessage()))
        .expectNextMatches(response -> response.getMessage().equals(message2.getMessage()))
        .thenCancel()
        .verify();
  }


  @Test
  void POST_message_성공() {
    MessageDto request = new MessageDto("id", "hello");

    webTestClient.post()
        .uri("/messages")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .exchange()
        .expectStatus()
        .isOk();

    // verify(...)로 실제로 publish()가 호출되었는지 확인.
    // argThat는 메서드가 호출될 때 전달된 인자가 특정 조건을 만족했는지 확인하는 데 사용하는 Mockito 도구입니다.
    verify(publisher).publish(argThat(dto -> dto.getMessage().equals("hello")));
  }
}