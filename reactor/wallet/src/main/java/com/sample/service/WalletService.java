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
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p> Wallet 기반 Service. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class WalletService {

  private final WalletRepository walletRepository;

  /**
   * id 로 조회.
   *
   * @param id 조회할 id
   * @return 응답
   */
  public Mono<WalletDto.Response> findById(Long id) {
    return walletRepository.findById(id)
        .map(w -> convertWallet(w))
        .switchIfEmpty(Mono.error(new RuntimeException("NOT FOUND")));
  }

  /**
   * UserId 로 조회.
   *
   * @param userId user Id
   * @return 응답
   */
  public Mono<WalletDto.Response> findByUserId(UUID userId) {
    return walletRepository.findByUserId(userId)
        .map(w -> convertWallet(w))
        .switchIfEmpty(Mono.error(new RuntimeException("NOT FOUND")));
  }

  /**
   * Wallet 저장.
   *
   * @param dto 저장 정보
   * @return 저장된 응답
   */
  @Transactional
  public Mono<WalletDto.Response> save(WalletDto.Create dto) {
    Wallet wallet = new Wallet(dto.getUserId());

    Mono<Wallet> walletMono = walletRepository.save(wallet);
    return walletMono.map(w -> convertWallet(w));
  }

  /**
   * Entity to Dto.
   *
   * @param wallet 지갑
   * @return dto
   */
  private WalletDto.Response convertWallet(Wallet wallet) {
    return new WalletDto.Response(wallet.getId(), wallet.getUserId(), wallet.getPoint(),
        wallet.getCreatedAt(), wallet.getModifiedAt());
  }
}