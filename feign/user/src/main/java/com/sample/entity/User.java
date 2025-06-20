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
import jakarta.persistence.Table;
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
 * <p>유저 entity. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`user`")
@EntityListeners(AuditingEntityListener.class)
public class User {

  /**
   * id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  /**
   * 이름.
   */
  @Column
  private String name;

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
   * @param name 이름
   */
  public User(String name) {
    this.name = name;
  }

  /**
   * 수정날짜.
   *
   * @param name 이름
   */
  public void update(String name) {
    this.name = name;
  }
}
