/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.controller;

import com.sample.dto.UserDto;
import com.sample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA.
 * create by IntelliJ IDEA.
 *
 * <p> UserController Handler 를 써야하지만, MVC 구조가 익술할 수 있기에. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  /**
   * 생성요청.
   *
   * @param dto dto.
   * @return 생성된 응답.
   */
  @PostMapping
  public Mono<ResponseEntity<UserDto.Response>> createUser(@RequestBody UserDto.Create dto) {
    Mono<UserDto.Response> user = userService.create(dto.getName());
    return user.map(u -> ResponseEntity.ok().body(u));
  }

  /**
   * 수정요청. 트랜젝션 테스트하고자 넣어봄.
   *
   * @param userDto UserDto.
   * @return User 응답.
   */
  @PutMapping
  public Mono<ResponseEntity<UserDto.Response>> updateUser(@RequestBody UserDto.Create userDto) {
    Mono<UserDto.Response> response = userService.transactionTest(userDto.getName());
    return response.map(r ->
            ResponseEntity.ok(r));
  }
}
