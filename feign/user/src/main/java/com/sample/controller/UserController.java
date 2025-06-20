/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.controller;

import com.sample.dto.UserDto;
import com.sample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA.
 * create by IntelliJ IDEA.
 *
 * <p> 유저 관련 Controller.. </p>
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
   * 생성.
   *
   * @param userDto User.
   * @return 응답
   */
  @PostMapping
  public ResponseEntity<UserDto.Response> createUser(@RequestBody UserDto.Create userDto) {
    UserDto.Response response = userService.create(userDto.getName());
    return ResponseEntity.ok(response);
  }

  /**
   * 수정.
   *
   * @param userDto user
   * @return 응답
   */
  @PutMapping
  public ResponseEntity<UserDto.Response> updateUser(@RequestBody UserDto.Create userDto) {
    UserDto.Response response = userService.transactionTest(userDto.getName());
    return ResponseEntity.ok(response);
  }
}
