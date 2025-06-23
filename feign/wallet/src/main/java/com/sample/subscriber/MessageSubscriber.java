/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.message.dto.MessageDto;
import com.sample.repository.EmitterRepository;
import com.sample.service.EmitterService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 구독정보. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class MessageSubscriber implements MessageListener {

  private final EmitterService emitterService;
  private final ObjectMapper objectMapper;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    try {
      String json = new String(message.getBody());
      MessageDto dto = objectMapper.readValue(json, MessageDto.class);
      emitterService.sendToClient(dto.getId(), dto.getMessage());
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
