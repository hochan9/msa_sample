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
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.data.relational.core.query.Criteria.where;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA.
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
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
  private final R2dbcEntityTemplate template;

  @Override
  public Mono<User> save(User user) {
    return template.insert(user);
  }

  @Override
  public Mono<User> update(User user) {
    return template.update(user);
  }

  @Override
  public Mono<User> findByName(String name) {
    return template.selectOne(Query.query(where("name").is(name)), User.class)
            .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
  }
}
