/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.message.dto;

import lombok.Getter;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 발행 관련. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Getter
public class MessageDto {

  /**
   * id.
   */
  private String id;

  /**
   * message.
   */
  private String message;

  /**
   * 생성자.
   *
   * @param id      userId
   * @param message 메시지
   */
  public MessageDto(String id, String message) {
    this.id = id;
    this.message = message;
  }
}
