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
import org.springframework.data.annotation.LastModifiedDate;

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
public class UserDto {

  @Getter
  public static class Create {
    private String name;
    public Create(String name) {
      this.name = name;
    }
  }

  @Getter
  public static class Response {
    private UUID id;
    private String name;
    private Long walletId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Response(UUID id, String name, Long walletId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
      this.id = id;
      this.name = name;
      this.walletId = walletId;
      this.createdAt = createdAt;
      this.modifiedAt = modifiedAt;
    }
  }
}
