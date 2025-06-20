/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.handler;

import com.sample.message.dto.MessageDto;
import com.sample.message.publisher.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 관련 핸들러. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 메시지 발행
 */
@Component
@RequiredArgsConstructor
public class MessageHandler {

  public final MessagePublisher messagePublisher;

  /**
   * 메시지 발행.
   *
   * @param request 요청
   * @return 응답
   */
  public Mono<ServerResponse> createMessage(ServerRequest request) {
    return request.bodyToMono(MessageDto.class)
        .doOnNext(m -> messagePublisher.publish(m))
        .then(ServerResponse.ok().build());
  }

}
