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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Sinks;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>Sink 관련 Repository. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Repository
public class SinkRepository {

  private final Map<String, Sinks.Many<MessageDto>> sinks = new ConcurrentHashMap<>();

  /**
   * 획득.
   *
   * @param userId userId.
   * @return Sink 정보
   */
  public Sinks.Many<MessageDto> get(String userId) {
    return sinks.computeIfAbsent(userId, k -> Sinks.many().multicast().onBackpressureBuffer());
  }

  /**
   * 삭제.
   *
   * @param userId userId
   */
  public void remove(String userId) {
    sinks.remove(userId);
  }
}
