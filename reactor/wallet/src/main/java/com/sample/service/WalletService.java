/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.service;

import com.sample.entity.Wallet;
import com.sample.repository.WalletRepository;
import com.sample.wallet.dto.WalletDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class WalletService {
  private final WalletRepository walletRepository;

  public Mono<WalletDto.Response> findById(Long id) {
    return walletRepository.findById(id)
            .map(w -> convertWallet(w))
            .switchIfEmpty(Mono.error(new RuntimeException("NOT FOUND")));
  }

  public Mono<WalletDto.Response> findByUserId(UUID userId) {
    return walletRepository.findByUserId(userId)
            .map(w -> convertWallet(w))
            .switchIfEmpty(Mono.error(new RuntimeException("NOT FOUND")));
  }

  @Transactional
  public Mono<WalletDto.Response> save(WalletDto.Create dto) {
    Wallet wallet = new Wallet(dto.getUserId());

    Mono<Wallet> walletMono = walletRepository.save(wallet);
    return walletMono.map(w -> convertWallet(w));
  }


  private WalletDto.Response convertWallet(Wallet wallet) {
    return new WalletDto.Response(wallet.getId(), wallet.getUserId(), wallet.getPoint(), wallet.getCreatedAt(), wallet.getModifiedAt());
  }
}