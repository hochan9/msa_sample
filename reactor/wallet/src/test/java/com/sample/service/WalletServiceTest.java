package com.sample.service;/*
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

import ch.qos.logback.core.model.processor.ModelHandlerException;
import com.sample.entity.Wallet;
import com.sample.repository.WalletRepository;
import com.sample.wallet.dto.WalletDto;
import com.sample.wallet.dto.WalletDto.Create;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@ExtendWith(MockitoExtension.class)
class WalletServiceTest {
  @Mock
  private WalletRepository walletRepository;

  @InjectMocks
  private WalletService walletService;

  @Test
  void findById_success() {
    // given
    Wallet wallet = Mockito.mock(Wallet.class);
    given(wallet.getId()).willReturn(1L);


    when(walletRepository.findById(anyLong())).thenReturn(Mono.just(wallet));

    StepVerifier.create(walletService.findById(1L))
        .expectNextMatches(response ->
          response.getId().equals(wallet.getId())
        )
        .verifyComplete();
  }

  @Test
  void findById_notFound() {
    when(walletRepository.findById(1L)).thenReturn(Mono.empty());

    StepVerifier.create(walletService.findById(1L))
        .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
            throwable.getMessage().equals("NOT FOUND"))
        .verify();
  }

  @Test
  void findByUserId_success() {
    // given
    Wallet wallet = Mockito.mock(Wallet.class);
    given(wallet.getId()).willReturn(1L);


    when(walletRepository.findByUserId(any())).thenReturn(Mono.just(wallet));

    StepVerifier.create(walletService.findByUserId(UUID.randomUUID()))
        .expectNextMatches(response ->
            response.getId().equals(wallet.getId())
        )
        .verifyComplete();
  }

  @Test
  void save() {
    Wallet wallet = Mockito.mock(Wallet.class);
    WalletDto.Create create = new Create(UUID.randomUUID());
    given(wallet.getId()).willReturn(1L);
    given(wallet.getUserId()).willReturn(create.getUserId());

    when(walletRepository.save(any())).thenReturn(Mono.just(wallet));

    StepVerifier.create(walletService.save(create))
        .expectNextMatches(response ->
            response.getId().equals(wallet.getId())
                && response.getUserId().equals(wallet.getUserId()))
        .verifyComplete();
  }
}