/*
 * Created by Hochan Son on 2025. 6. 23.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 23.
 */

package com.sample.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.message.dto.MessageDto;
import com.sample.repository.SinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 구독정보. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class MessageSubscriber implements MessageListener {
  private final ObjectMapper objectMapper;
  private final SinkRepository sinkRepository;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    try {
      String json = new String(message.getBody());
      MessageDto dto = objectMapper.readValue(json, MessageDto.class);
      sinkRepository.get(dto.getId()).tryEmitNext(dto);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
