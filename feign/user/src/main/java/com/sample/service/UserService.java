/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.service;

import com.sample.dto.UserDto;
import com.sample.entity.User;
import com.sample.repository.UserRepository;
import com.sample.wallet.client.WalletClient;
import com.sample.wallet.dto.WalletDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>유저 Service. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  private final WalletClient walletClient;


  /**
   * 생성.
   *
   * @param name 이름
   * @return 응답
   */
  @Transactional
  public UserDto.Response create(String name) {
    User user = new User(name);

    user = userRepository.save(user);
    WalletDto.Response walletDto = walletClient.create(new WalletDto.Create(user.getId()))
        .getBody();
    return new UserDto.Response(user.getId(),
        user.getName(),
        walletDto.getId());
  }

  /**
   * 트랜잭션 테스트.
   *
   * @param name 이름
   * @return 응답
   */
  @Transactional
  public UserDto.Response transactionTest(String name) {
    User user = userRepository.findByName(name)
        .orElseThrow(() -> new RuntimeException("NOT FOUND"));

    user.update(name + "1");
    WalletDto.Response walletDto = walletClient.create(new WalletDto.Create(user.getId()))
        .getBody();
    return new UserDto.Response(user.getId(),
        user.getName(),
        walletDto.getId());
  }
}
