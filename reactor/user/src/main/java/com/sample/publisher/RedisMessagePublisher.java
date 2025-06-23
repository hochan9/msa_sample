/*
 * Created by Hochan Son on 2025. 6. 23.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 23.
 */

package com.sample.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.message.dto.MessageDto;
import com.sample.message.entity.MessageChannel;
import com.sample.message.publisher.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 발행. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
@Component
public class RedisMessagePublisher implements MessagePublisher {

  private final RedisTemplate<String, String> redisTemplate;
  private final ObjectMapper objectMapper;

  @Override
  public void publish(MessageDto message) {
    try {
      String json = objectMapper.writeValueAsString(message);
      redisTemplate.convertAndSend(MessageChannel.DEFAULT.getName(), json);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
