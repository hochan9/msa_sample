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

import java.util.UUID;
import lombok.Getter;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p> 유저 응답.. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public class UserDto {

  /**
   * 생성 요청.
   */
  @Getter
  public static class Create {

    /**
     * 이름.
     */
    private String name;

    /**
     * 생성자.
     *
     * @param name 이름
     */
    public Create(String name) {
      this.name = name;
    }
  }

  /**
   * 응답.
   */
  @Getter
  public static class Response {

    /**
     * userId.
     */
    private UUID id;

    /**
     * 이름.
     */
    private String name;

    /**
     * 지갑 id.
     */
    private Long walletId;

    /**
     * 생성자.
     *
     * @param id       id
     * @param name     이름
     * @param walletId 지갑id
     */
    public Response(UUID id, String name, Long walletId) {
      this.id = id;
      this.name = name;
      this.walletId = walletId;
    }
  }
}
