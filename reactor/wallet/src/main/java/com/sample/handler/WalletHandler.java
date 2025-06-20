/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.handler;

import com.sample.service.WalletService;
import com.sample.wallet.dto.WalletDto;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>리액티브 전용 핸들러. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class WalletHandler {

  private final WalletService walletService;

  /**
   * wallet 찾기.
   *
   * @param request 요청
   * @return 응답.
   */
  public Mono<ServerResponse> findById(ServerRequest request) {
    Long id = Long.parseLong(request.pathVariable("id"));
    return walletService.findById(id)
        .flatMap(wallet -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(wallet))
        .onErrorResume(error -> ServerResponse.badRequest()
            .build());
  }

  /**
   * User Id 로 조회.
   *
   * @param request 요청
   * @return 응답
   */
  public Mono<ServerResponse> findByUserId(ServerRequest request) {
    UUID userId = UUID.fromString(request.queryParam("userId")
        .orElseThrow(() -> new IllegalArgumentException("userId is required")));
    return walletService.findByUserId(userId)
        .flatMap(wallet -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(wallet))
        .onErrorResume(error -> ServerResponse.badRequest()
            .build());
  }

  /**
   * 생성요청.
   *
   * @param request 요청
   * @return 응답
   */
  public Mono<ServerResponse> create(ServerRequest request) {
    return request.bodyToMono(WalletDto.Create.class)
        .flatMap(dto -> walletService.save(dto))
        .flatMap(wallet ->
            ServerResponse.created(URI.create("/wallets/" + wallet.getId())).bodyValue(wallet))
//            ServerResponse.status(201).bodyValue(walletService.save(dto)))
        .onErrorResume(error -> ServerResponse.badRequest().build());
  }
}
