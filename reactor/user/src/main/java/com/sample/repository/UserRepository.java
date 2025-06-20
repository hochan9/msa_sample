/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.repository;

import com.sample.entity.User;
import reactor.core.publisher.Mono;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p> 사용자 Repository. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public interface UserRepository {

  /**
   * 저장.
   *
   * @param user 저장할 user
   * @return {@code Mono<User>}
   */
  Mono<User> save(User user);

  /**
   * 수정.
   *
   * @param user 수정할 user
   * @return {@code Mono<User>}
   */
  Mono<User> update(User user);

  /**
   * 이름으로 조회.
   *
   * @param name 이름
   * @return {@code Mono<User>}
   */
  Mono<User> findByName(String name);
}
