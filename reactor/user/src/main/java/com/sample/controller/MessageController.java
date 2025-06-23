/*
 * Created by Hochan Son on 2025. 6. 23.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 23.
 */

package com.sample.controller;

import com.sample.message.dto.MessageDto;
import com.sample.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>Message 관련 Controller. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  /**
   * SSE 연결.
   *
   * @param userId userId
   * @return Message 정보
   */
  @GetMapping(value = "/streaming-messages/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<MessageDto> streamingMessages(@PathVariable String userId) {
    return messageService.subscribe(userId);
  }

  /**
   * 메시지 발행.
   *
   * @param messageDto 메시지 정보
   * @return 응답
   */
  @PostMapping("/messages")
  public Mono<ResponseEntity<Void>> publish(@RequestBody MessageDto messageDto) {
    messageService.publish(messageDto);
    return Mono.just(ResponseEntity.ok().build());
  }
}
