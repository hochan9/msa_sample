package com.sample.router;/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.sample.entity.Wallet;
import com.sample.handler.WalletHandler;
import com.sample.service.WalletService;
import com.sample.wallet.dto.WalletDto;
import com.sample.wallet.dto.WalletDto.Create;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.annotation.Immutable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>클래스 설명. </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import({WalletRouter.class, WalletHandler.class})
class WalletRouterTest {
  @Autowired
  private WebTestClient webClient;

  @MockitoBean
  private WalletService walletService;


  @Test
  void GET_findById_Success() {
    // given
    WalletDto.Response wallet = Mockito.mock(WalletDto.Response.class);
    given(wallet.getId()).willReturn(1L);

    // when
    when(walletService.findById(anyLong())).thenReturn(Mono.just(wallet));

    // then
    webClient.get().uri("/wallets/1")
        .exchange()
        .expectStatus()
        .isOk()
        .returnResult(WalletDto.Response.class)
        .getResponseBody()
        .as(StepVerifier::create)
        .expectNextMatches(walletDto -> walletDto.getId().equals(wallet.getId()))
        .verifyComplete();
  }

  @Test
  void GET_findById_NotFound() {
    when(walletService.findById(anyLong())).thenReturn(Mono.error(new RuntimeException("NOT FOUND")));

    webClient.get().uri("/wallets/1")
        .exchange()
        .expectStatus()
        .is4xxClientError()
        .expectBody()
        .consumeWith(result -> assertThat(result.getResponseBody()).contains("NOT FOUND".getBytes()));
  }


  @Test
  void POST_save_Success() {
    // given
    WalletDto.Create create = new Create(UUID.randomUUID());
    WalletDto.Response wallet = Mockito.mock(WalletDto.Response.class);
    given(wallet.getUserId()).willReturn(create.getUserId());
    given(wallet.getId()).willReturn(1L);

    // when
    when(walletService.save(any())).thenReturn(Mono.just(wallet));

    // then
    webClient.post().uri("/wallets")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(create)
        .exchange()
        .expectStatus()
        .isCreated()
        .returnResult(WalletDto.Response.class)
        .getResponseBody()
        .as(StepVerifier::create)
        .expectNextMatches(walletDto -> walletDto.getId().equals(wallet.getId()))
        .verifyComplete();
  }
}