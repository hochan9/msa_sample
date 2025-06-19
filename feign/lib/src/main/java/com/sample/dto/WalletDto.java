/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Getter
public class WalletDto {
  private Long id;
  private UUID userId;
  private BigDecimal point;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public WalletDto(Long id, UUID userId, BigDecimal point, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    this.id = id;
    this.userId = userId;
    this.point = point;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
  }
}
