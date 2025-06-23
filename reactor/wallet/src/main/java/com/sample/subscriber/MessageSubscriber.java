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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.message.dto.MessageDto;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 구독. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MessageSubscriber implements MessageListener {

  private final ObjectMapper objectMapper;

//  @Getter
//  private final Sinks.Many<MessageDto> sink = Sinks.many().multicast().onBackpressureBuffer();
//
//  /**
//   * 메시지 구독시.
//   *
//   * @param message 메시지 정보
//   * @param pattern 패턴?
//   */
//  @Override
//  public void onMessage(Message message, byte[] pattern) {
//    try {
//      String json = new String(message.getBody());
//      MessageDto dto = objectMapper.readValue(json, MessageDto.class);
//      sink.tryEmitNext(dto);
//    } catch (JsonMappingException e) {
//      throw new RuntimeException(e);
//    } catch (JsonProcessingException e) {
//      throw new RuntimeException(e);
//    }
//  }

  // userId -> Sink
  private final Map<String, Many<MessageDto>> sinks = new ConcurrentHashMap<>();

  /**
   * userId 에 해당하는 sink 반환 (없으면 생성).
   */
  public Sinks.Many<MessageDto> getSink(String userId) {
    return sinks.computeIfAbsent(userId, id ->
        Sinks.many().multicast().onBackpressureBuffer());
  }

  @Override
  public void onMessage(Message message, byte[] pattern) {
    try {
      String json = new String(message.getBody());
      MessageDto dto = objectMapper.readValue(json, MessageDto.class);
      // userId 에 해당하는 sink 에만 emit
      Sinks.Many<MessageDto> sink = sinks.get(dto.getId());
      if (sink != null) {
        sink.tryEmitNext(dto);
      } else {
        log.warn("No sink found for id: {}", dto.getId());
      }
    } catch (JsonMappingException e) {
      throw new RuntimeException(e);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
