/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.controller;

import com.sample.client.WalletClient;
import com.sample.dto.WalletCreateDto;
import com.sample.dto.WalletDto;
import com.sample.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController implements WalletClient {
  private final WalletService walletService;

  @Override
  public ResponseEntity<WalletDto> findById(@PathVariable Long id) {
    WalletDto dto = walletService.findById(id);
    return ResponseEntity.ok(dto);
  }

  @Override
  public ResponseEntity<WalletDto> findByUserId(UUID userId) {
    WalletDto dto = walletService.findByUserId(userId);
    return ResponseEntity.ok(dto);
  }

  @Override
  public ResponseEntity<WalletDto> create(WalletCreateDto dto) {
    WalletDto walletDto = walletService.save(dto);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(walletDto);
  }
}
