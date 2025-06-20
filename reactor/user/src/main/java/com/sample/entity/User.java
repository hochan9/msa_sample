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

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA.
 * create by IntelliJ IDEA.
 *
 * <p> 유저 entity.. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`user`")
public class User {

  /**
   * id.
   */
  @Id
  private UUID id;

  /**
   * 이름.
   */
  private String name;

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
   * @param id id
   * @param name 이름
   */
  public User(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * 수정.
   *
   * @param name 이름
   */
  public void update(String name) {
    this.name = name;
  }
}
