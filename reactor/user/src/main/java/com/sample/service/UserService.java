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

import com.sample.config.WebClientConfig;
import com.sample.dto.UserDto;
import com.sample.entity.User;
import com.sample.repository.UserRepository;
import com.sample.wallet.dto.WalletDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final WebClient walletWebClient;

  public Mono<UserDto.Response> create(String name) {
    UUID id = UUID.randomUUID();
    User user = new User(id, name);

    Mono<User> mono = userRepository.save(user);
    return mono
            .flatMap(u ->
                    walletWebClient.post()
                            .uri("/wallets")
                            .bodyValue(new WalletDto.Create(u.getId()))
                            .retrieve()
                            .bodyToMono(WalletDto.Response.class)
                            .map(w -> new UserDto.Response(u.getId(), u.getName(), w.getId(),
                                    u.getCreatedAt(),
                                    u.getModifiedAt()))
            );
  }

  public Mono<UserDto.Response> transactionTest(String name) {
    Mono<User> user = userRepository.findByName(name);
    return user.flatMap(u -> {
              u.update(name + " 1");
              return userRepository.update(u);
            })
            .flatMap(u ->
                    walletWebClient.post()
                            .uri("/wallets")
                            .bodyValue(new WalletDto.Create(u.getId()))
                            .retrieve()
                            .bodyToMono(WalletDto.Response.class)
                            .map(w -> new UserDto.Response(u.getId(), u.getName(), w.getId(),
                                    u.getCreatedAt(),
                                    u.getModifiedAt()))
            );
  }
}
