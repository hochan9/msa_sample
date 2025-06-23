package com.sample.service;/*
 * Created by Hochan Son on 2025. 6. 23.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 23.
 */

import static org.assertj.core.api.Assertions.setAllowComparingPrivateFields;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.sample.message.dto.MessageDto;
import com.sample.publisher.RedisMessagePublisher;
import com.sample.repository.SinkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
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
@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

  @InjectMocks
  private MessageService messageService;

  @Mock
  private SinkRepository sinkRepository;

  @Mock
  private RedisMessagePublisher redisMessagePublisher;


  @Test
  void subscribeSuccess() {
    String userId = "user";
    MessageDto messageDto = new MessageDto(userId, "message");

    Sinks.Many<MessageDto> sinks = Sinks.many().unicast().onBackpressureBuffer();
    sinks.tryEmitNext(messageDto);

    when(sinkRepository.get(userId)).thenReturn(sinks);

    Flux<MessageDto> result = messageService.subscribe(userId);

    StepVerifier.create(result)
        .expectNextMatches(r -> r.getMessage().equals(messageDto.getMessage()))
        .thenCancel()
        .verify();

    verify(sinkRepository).remove(userId);

  }

  @Test
  void publish() {
    String userId = "user";
    MessageDto messageDto = new MessageDto(userId, "message");

    Mono<Void> result = messageService.publish(messageDto);

    StepVerifier.create(result)
        .verifyComplete();

    verify(redisMessagePublisher).publish(messageDto);
    verify(redisMessagePublisher).publish(argThat(dto -> dto.getMessage().equals(messageDto.getMessage())));
  }
}