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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.message.dto.MessageDto;
import com.sample.message.entity.MessageChannel;
import com.sample.publisher.RedisMessagePublisher;
import com.sample.repository.SinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>Message 관련 Service. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class MessageService {

  private final SinkRepository sinkRepository;
  private final RedisMessagePublisher redisMessagePublisher;

  /**
   * 구독.
   *
   * @param userId userId
   * @return 메시지
   */
  public Flux<MessageDto> subscribe(String userId) {
    return sinkRepository.get(userId)
        .asFlux()
        .doFinally(sink -> sinkRepository.remove(userId));
  }

  /**
   * 발행.
   *
   * @param dto 발행 메시지.
   */
  public void publish(MessageDto dto) {
    redisMessagePublisher.publish(dto);
  }

}
