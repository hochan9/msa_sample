/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.sample.handler.MessageHandler;
import com.sample.message.dto.MessageDto;
import com.sample.subscriber.MessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Sinks;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 관련 라우터. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class MessageRouter {

  /**
   * 메시지 라우터.
   *
   * @param subscriber 구독자
   * @param handler    핸들러
   * @return 응답
   */
  @Bean
  public RouterFunction<ServerResponse> routeMessage(MessageSubscriber subscriber,
      MessageHandler handler) {
    return route(
        GET("/streaming-messages/{userId}"),
        request -> {
          String userId = request.pathVariable("userId");
          Sinks.Many<MessageDto> sink = subscriber.getSink(userId);
          return ServerResponse.ok()
              .contentType(MediaType.TEXT_EVENT_STREAM)
              .body(sink.asFlux(), MessageDto.class);  // 이렇게 변경
        }
    )
        .andRoute(POST("/messages"), handler::createMessage);
  }
}
