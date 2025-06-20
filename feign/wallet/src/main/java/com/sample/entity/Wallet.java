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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p> entity.. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Wallet {

  /**
   * id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * user Id.
   */
  @Column(unique = true)
  private UUID userId;

  /**
   * 잔액.
   */
  @Column
  private BigDecimal point;

  /**
   * 생성날짜.
   */
  @CreatedDate
  private LocalDateTime createdAt;

  /**
   * 수정날짜.
   */
  @LastModifiedDate
  private LocalDateTime modifiedAt;

  /**
   * 생성자.
   *
   * @param userId userId.
   */
  public Wallet(UUID userId) {
    this.userId = userId;
    this.point = BigDecimal.ZERO;
  }
}
