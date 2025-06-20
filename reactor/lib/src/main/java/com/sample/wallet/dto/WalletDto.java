/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.wallet.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p> 지갑 dto. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public class WalletDto {

  /**
   * 생성.
   */
  @Getter
  public static class Create {

    /**
     * userId.
     */
    private UUID userId;

    /**
     * 생성자.
     *
     * @param userId userId
     */
    public Create(UUID userId) {
      this.userId = userId;
    }
  }

  /**
   * 응답.
   */
  @Getter
  public static class Response {

    /**
     * id.
     */
    private Long id;

    /**
     * userId.
     */
    private UUID userId;

    /**
     * 잔액.
     */
    private BigDecimal point;

    /**
     * 생성시간.
     */
    private LocalDateTime createdAt;

    /**
     * 수정시간.
     */
    private LocalDateTime modifiedAt;

    /**
     * 응답.
     *
     * @param id         id
     * @param userId     userId
     * @param point      잔액
     * @param createdAt  생성시간
     * @param modifiedAt 수정시간
     */
    public Response(Long id, UUID userId, BigDecimal point, LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
      this.id = id;
      this.userId = userId;
      this.point = point;
      this.createdAt = createdAt;
      this.modifiedAt = modifiedAt;
    }
  }
}
