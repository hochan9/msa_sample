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

import com.sample.dto.WalletCreateDto;
import com.sample.dto.WalletDto;
import com.sample.entity.Wallet;
import com.sample.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  public WalletDto findById(Long id) {
    Wallet wallet = walletRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("NOT FOUND"));
    return convertWallet(wallet);
  }

  public WalletDto findByUserId(UUID userId) {
    Wallet wallet = walletRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("NOT FOUND"));
    return convertWallet(wallet);
  }

  @Transactional
  public WalletDto save(WalletCreateDto dto) {
    Wallet wallet = new Wallet(dto.getUserId());

    return convertWallet(walletRepository.save(wallet));
  }

  private WalletDto convertWallet(Wallet wallet) {
    return new WalletDto(wallet.getId(), wallet.getUserId(), wallet.getPoint(), wallet.getCreatedAt(), wallet.getModifiedAt());
  }
}
