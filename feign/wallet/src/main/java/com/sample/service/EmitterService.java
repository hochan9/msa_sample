/*
 * Created by Hochan Son on 2025. 6. 23.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 23.
 */

package com.sample.service;

import com.sample.repository.EmitterRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>sse 관련 Service. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmitterService {

  private final EmitterRepository repository;

  /**
   * 구독.
   *
   * @param userId userId
   * @return SseEmitter
   */
  public SseEmitter subscribe(String userId) {
    SseEmitter emitter = new SseEmitter(60 * 1000L);
    repository.save(userId, emitter);
    return emitter;
  }

  /**
   * 발행하기.
   *
   * @param userId  userId
   * @param message 메시지.
   */
  public void sendToClient(String userId, String message) {
    repository.get(userId).ifPresentOrElse(emitter -> {
      try {
        emitter.send(SseEmitter.event()
            .data(message));
      } catch (IOException e) {
        emitter.completeWithError(e);
        repository.remove(userId);
      }
    }, () -> {
      // 없으면 무시하거나 로그 처리
      log.error(message);
    });
  }
}
