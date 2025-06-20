/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>지갑 entity. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet {

  /**
   * id.
   */
  @Id
  private Long id;

  /**
   * UserId.
   */
  private UUID userId;

  /**
   * 잔액.
   */
  private BigDecimal point;

  /**
   * 생성시간.
   */
  @CreatedDate
  private LocalDateTime createdAt;

  /**
   * 수정시간.
   */
  @LastModifiedDate
  private LocalDateTime modifiedAt;

  /**
   * 생성자.
   *
   * @param userId 유저 id.
   */
  public Wallet(UUID userId) {
    this.userId = userId;
    this.point = BigDecimal.ZERO;
  }
}

