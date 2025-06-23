package com.sample.service;/*
 * Created by Hochan Son on 2025. 6. 23.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 23.
 */

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.sample.dto.UserDto;
import com.sample.entity.User;
import com.sample.repository.UserRepository;
import com.sample.wallet.dto.WalletDto;
import com.sample.wallet.dto.WalletDto.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>클래스 설명. </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private WebClient walletWebClient;

  @Mock
  private WebClient.RequestBodyUriSpec requestBodyUriSpec;

  @Mock
  private WebClient.RequestBodySpec requestBodySpec;

  @Mock
  private WebClient.RequestHeadersSpec requestHeadersSpec;

  @Mock
  private WebClient.ResponseSpec responseSpec;

  @Test
  void createSuccess() {
    //given
    String name = "user";
    UUID uuid = UUID.randomUUID();
    User user = new User(uuid, name);

    WalletDto.Response walletResponse = new Response(1L, uuid, BigDecimal.ZERO, LocalDateTime.now(), LocalDateTime.now());
    UserDto.Response response = new UserDto.Response(uuid, name, walletResponse.getId(), user.getCreatedAt(), user.getModifiedAt());

    // when
    when(userRepository.save(any())).thenReturn(Mono.just(user));
    when(walletWebClient.post()).thenReturn(requestBodyUriSpec);
    when(requestBodyUriSpec.uri("/wallets")).thenReturn(requestBodySpec);
    when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    when(responseSpec.bodyToMono(WalletDto.Response.class)).thenReturn(Mono.just(walletResponse));

    Mono<UserDto.Response> result = userService.create(name);

    // then
    StepVerifier.create(result)
        .expectNextMatches(res ->
            res.getId().equals(response.getId()) &&
                res.getName().equals(response.getName()) &&
                res.getWalletId().equals(response.getWalletId()))
        .verifyComplete();

    verify(userRepository).save(any());
    verify(walletWebClient).post();
  }

  @Test
  void transactionTest() {
    // given
    String name = "name";
    UUID userId = UUID.randomUUID();
    User existingUser = new User(userId, name);

    // 수정된 이름
    User updatedUser = new User(userId, name + " 1");

    WalletDto.Response walletResponse = new Response(1L, userId, BigDecimal.ZERO, LocalDateTime.now(), LocalDateTime.now());

    when(userRepository.findByName(name)).thenReturn(Mono.just(existingUser));
    when(userRepository.update(any())).thenReturn(Mono.just(updatedUser));

    // WebClient mocking
    when(walletWebClient.post()).thenReturn(requestBodyUriSpec);
    when(requestBodyUriSpec.uri("/wallets")).thenReturn(requestBodySpec);
    when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    when(responseSpec.bodyToMono(WalletDto.Response.class)).thenReturn(Mono.just(walletResponse));

    // when
    Mono<UserDto.Response> result = userService.transactionTest(name);

    // then
    StepVerifier.create(result)
        .expectNextMatches(res ->
            res.getId().equals(userId) &&
                res.getName().equals(name + " 1") &&
                res.getWalletId().equals(walletResponse.getId()))
        .verifyComplete();

    verify(userRepository).findByName(name);
    verify(userRepository).update(any());
    verify(walletWebClient).post();
  }
}