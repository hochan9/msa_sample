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
import com.sample.message.publisher.MessagePublisher;
import com.sample.publisher.RedisMessagePublisher;
import com.sample.service.EmitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 관련 Test. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
public class MessageController {
  private final EmitterService service;
  private final MessagePublisher publisher;

  /**
   * SSe Message 구독.
   *
   * @param userId 구독할 id
   * @return 응답
   */
  @GetMapping("/streaming-messages/{userId}")
  public SseEmitter streamingMessages(@PathVariable String userId) {
    return service.subscribe(userId);
  }

  /**
   * 메시지 발행.
   *
   * @param dto 발행할 Dto
   * @return 으답
   */
  @PostMapping("/messages")
  public ResponseEntity<Void> publishMessage(@RequestBody MessageDto dto) {
    publisher.publish(dto);
    return ResponseEntity.ok().build();
  }
}
