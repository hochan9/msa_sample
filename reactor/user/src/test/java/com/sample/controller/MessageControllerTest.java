package com.sample.controller;/*
 * Created by Hochan Son on 2025. 6. 23.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 23.
 */

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.sample.message.dto.MessageDto;
import com.sample.service.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>클래스 설명. </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@WebFluxTest(controllers = MessageController.class)
class MessageControllerTest {

  @Autowired
  private WebTestClient webClient;

  @MockitoBean
  private MessageService messageService;

  @Test
  void streamingMessagesSuccess() {
    //given
    MessageDto messageDto1 = new MessageDto("user", "message1");
    MessageDto messageDto2 = new MessageDto("user", "message2");

//    Sinks.Many<MessageDto> sink = Sinks.many().replay().all();
    when(messageService.subscribe(anyString())).thenReturn(Flux.just(messageDto1, messageDto2));

    // then
    webClient.get().uri("/streaming-messages/{userId}", messageDto1.getId())
        .accept(MediaType.TEXT_EVENT_STREAM)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM_VALUE)
        .returnResult(MessageDto.class)
        .getResponseBody()
        .as(StepVerifier::create)
        .expectNextMatches(response -> response.getMessage().equals(messageDto1.getMessage()))
        .expectNextMatches(response -> response.getMessage().equals(messageDto2.getMessage()))
        .thenCancel()
        .verify();
  }

  @Test
  void publishSuccess() {
    // given
    MessageDto messageDto = new MessageDto("user", "message1");

    // when
    when(messageService.publish(any())).thenReturn(Mono.empty());


    // then
    webClient.post().uri("/messages")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(messageDto)
        .exchange()
        .expectStatus().isOk();

    // verify(...)로 실제로 publish()가 호출되었는지 확인.
    // argThat는 메서드가 호출될 때 전달된 인자가 특정 조건을 만족했는지 확인하는 데 사용하는 Mockito 도구입니다.
    verify(messageService).publish(argThat(dto -> dto.getMessage().equals("message1")));

    // ArgumentCaptor 사용
    ArgumentCaptor<MessageDto> captor = ArgumentCaptor.forClass(MessageDto.class);
    verify(messageService).publish(captor.capture());
    MessageDto actual = captor.getValue();

    assertThat(actual.getId()).isEqualTo(messageDto.getId());
    assertThat(actual.getMessage()).isEqualTo(messageDto.getMessage());
  }
}