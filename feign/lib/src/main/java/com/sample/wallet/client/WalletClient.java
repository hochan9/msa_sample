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
 * <p> Feign 관련 interface. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public interface WalletClient {

  /**
   * 조회.
   *
   * @param id id
   * @return 응답
   */
  @GetMapping("/{id}")
  ResponseEntity<WalletDto.Response> findById(@PathVariable Long id);

  /**
   * User Id 로 조회.
   *
   * @param userId user Id
   * @return 응답
   */
  @GetMapping
  ResponseEntity<WalletDto.Response> findByUserId(@RequestParam(name = "userId") UUID userId);

  /**
   * 생성.
   *
   * @param dto dto
   * @return 응답
   */
  @PostMapping
  ResponseEntity<WalletDto.Response> create(@RequestBody WalletDto.Create dto);
}
