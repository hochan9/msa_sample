/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.service;

import com.sample.dto.UserDto;
import com.sample.entity.User;
import com.sample.repository.UserRepository;
import com.sample.wallet.dto.WalletDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p> User 관련 리액티브 Service.. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final WebClient walletWebClient;

  /**
   * 생성.
   *
   * @param name 이름
   * @return 유저 응답.
   */
  public Mono<UserDto.Response> create(String name) {
    UUID id = UUID.randomUUID();
    User user = new User(id, name);

    Mono<User> mono = userRepository.save(user);
    return mono.flatMap(u -> createWallet(u));
  }

  /**
   * 트랜잭션 테스트. 하지만 되진않는다.
   *
   * @param name 이름
   * @return User
   */
  public Mono<UserDto.Response> transactionTest(String name) {
    Mono<User> user = userRepository.findByName(name);
    return user.flatMap(u -> {
          u.update(name + " 1");
          return userRepository.update(u);
        })
        .flatMap(u -> createWallet(u));
  }

  /**
   * Webcliet 를 통한 비동기 통신.
   *
   * @param user 저장할 user.
   * @return UserResponse
   */
  private Mono<UserDto.Response> createWallet(User user) {
    return walletWebClient.post()
        .uri("/wallets")
        .bodyValue(new WalletDto.Create(user.getId()))
        .retrieve()
        .bodyToMono(WalletDto.Response.class)
        .map(w -> new UserDto.Response(user.getId(), user.getName(), w.getId(),
            user.getCreatedAt(),
            user.getModifiedAt()));
  }
}
