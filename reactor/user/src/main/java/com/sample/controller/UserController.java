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
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping
  public Mono<ResponseEntity<UserDto.Response>> createUser(@RequestBody UserDto.Create dto) {
    Mono<UserDto.Response> user = userService.create(dto.getName());
    return user.map(u -> ResponseEntity.ok().body(u));
  }

  @PutMapping
  public Mono<ResponseEntity<UserDto.Response>> updateUser(@RequestBody UserDto.Create userDto) {
    Mono<UserDto.Response> response = userService.transactionTest(userDto.getName());
    return response.map(r ->
            ResponseEntity.ok(r));
  }
}
