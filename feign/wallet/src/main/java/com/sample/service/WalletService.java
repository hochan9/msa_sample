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

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p> wallet Service. </p>
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
   * @param id id
   * @return 응답.
   */
  public WalletDto.Response findById(Long id) {
    Wallet wallet = walletRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("NOT FOUND"));
    return convertWallet(wallet);
  }

  /**
   * userId 로 조회.
   *
   * @param userId userId
   * @return 응답
   */
  public WalletDto.Response findByUserId(UUID userId) {
    Wallet wallet = walletRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("NOT FOUND"));
    return convertWallet(wallet);
  }

  /**
   * 저장.
   *
   * @param dto dto
   * @return 응답
   */
  @Transactional
  public WalletDto.Response save(WalletDto.Create dto) {
    Wallet wallet = new Wallet(dto.getUserId());

    return convertWallet(walletRepository.save(wallet));
  }

  /**
   * entity to dto.
   * todo: 정적팩토리
   *
   * @param wallet 지갑
   * @return dto
   */
  private WalletDto.Response convertWallet(Wallet wallet) {
    return new WalletDto.Response(wallet.getId(), wallet.getUserId(), wallet.getPoint(),
        wallet.getCreatedAt(), wallet.getModifiedAt());
  }
}
