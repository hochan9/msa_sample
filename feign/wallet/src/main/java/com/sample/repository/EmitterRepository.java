/*
 * Created by Hochan Son on 2025. 6. 23.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 23.
 */

package com.sample.repository;

import com.sample.message.dto.MessageDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Sse;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>SSE 관련 Repository. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Repository
public class EmitterRepository {

  private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

  /**
   * 저장.
   *
   * @param userId     유적 id
   * @param sseEmitter sseEmitter
   */
  public void save(String userId, SseEmitter sseEmitter) {
    emitters.put(userId, sseEmitter);
    sseEmitter.onCompletion(() -> emitters.remove(userId));
    sseEmitter.onTimeout(() -> emitters.remove(userId));
  }

  /**
   * 조회.
   *
   * @param id 조회할 userId
   * @return 조회정보
   */
  public Optional<SseEmitter> get(String id) {
    return Optional.ofNullable(emitters.get(id));
  }

  /**
   * 삭제.
   *
   * @param userId userId
   */
  public void remove(String userId) {
    emitters.remove(userId);
  }
}
