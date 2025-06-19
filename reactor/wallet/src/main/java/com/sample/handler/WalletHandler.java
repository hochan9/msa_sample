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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA.
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명. </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Component
@RequiredArgsConstructor
public class WalletHandler {
  private final WalletService walletService;

  public Mono<ServerResponse> findById(ServerRequest request) {
    Long id = Long.parseLong(request.pathVariable("id"));
    return walletService.findById(id)
            .flatMap(wallet -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(wallet))
            .onErrorResume(error -> ServerResponse.badRequest()
                    .build());
  }

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

  public Mono<ServerResponse> create(ServerRequest request) {
    return request.bodyToMono(WalletDto.Create.class)
            .flatMap(dto -> walletService.save(dto))
            .flatMap(wallet ->
                    ServerResponse.created(URI.create("/wallets/" + wallet.getId())).bodyValue(wallet))
//            ServerResponse.status(201).bodyValue(walletService.save(dto)))
            .onErrorResume(error -> ServerResponse.badRequest().build());
  }
}
