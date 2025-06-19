/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.wallet.client;

import com.sample.wallet.dto.WalletDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public interface WalletClient {

  @GetMapping("/{id}")
  ResponseEntity<WalletDto.Response> findById(@PathVariable Long id);

  @GetMapping
  ResponseEntity<WalletDto.Response> findByUserId(@RequestParam(name = "userId") UUID userId);

  @PostMapping
  ResponseEntity<WalletDto.Response> create(@RequestBody WalletDto.Create dto);
}
