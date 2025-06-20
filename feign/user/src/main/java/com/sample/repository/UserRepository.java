/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.repository;

import com.sample.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>User Repository.. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public interface UserRepository extends JpaRepository<User, UUID> {

  /**
   * 이름으로 User 검색.
   *
   * @param name 이름
   * @return User
   */
  Optional<User> findByName(String name);
}
