package com.sample.controller;/*
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
import com.sample.dto.UserDto.Create;
import com.sample.dto.UserDto.Response;
import com.sample.service.UserService;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
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
@WebFluxTest(UserController.class)
class UserControllerTest {

  @Autowired
  private WebTestClient webClient;

  @MockitoBean
  private UserService userService;

  private String baseUrl = "/users";

  @Test
  void createUserSuccess() {
    // given
    UserDto.Create create = new Create("user");
    UserDto.Response response = new Response(UUID.randomUUID(), create.getName(), 1L,
        LocalDateTime.now(), LocalDateTime.now());

    // when
    when(userService.create(any())).thenReturn(Mono.just(response));


    // then
    webClient.post().uri(baseUrl)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(create)
        .exchange()
        .expectStatus().isCreated()
        .returnResult(UserDto.Response.class)
        .getResponseBody()
        .as(StepVerifier::create)
        .expectNextMatches(dto -> dto.getName().equals(create.getName()))
        .verifyComplete();
  }

  @Test
  void updateUserSuccess() {
    // given
    UserDto.Create create = new Create("user");
    UserDto.Response response = new Response(UUID.randomUUID(), create.getName(), 1L,
        LocalDateTime.now(), LocalDateTime.now());

    // when
    when(userService.transactionTest(any())).thenReturn(Mono.just(response));

    // then
    webClient.put()
        .uri(baseUrl)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(create)
        .exchange()
        .expectStatus().isOk()
        .returnResult(UserDto.Response.class)
        .getResponseBody()
        .as(StepVerifier::create)
        .expectNextMatches(dto -> dto.getName().equals(create.getName()))
        .verifyComplete();
  }
}