/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.message.dto.MessageDto;
import com.sample.message.entity.MessageChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 발행 정보. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MessagePublisher implements com.sample.message.publisher.MessagePublisher {

  private final RedisTemplate redisTemplate;
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
