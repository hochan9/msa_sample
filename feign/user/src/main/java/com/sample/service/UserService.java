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

import com.sample.wallet.client.WalletClient;
import com.sample.dto.UserDto;
import com.sample.wallet.dto.WalletDto;
import com.sample.entity.User;
import com.sample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA.
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
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
  private final UserRepository userRepository;

  private final WalletClient walletClient;


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

  @Transactional
  public UserDto.Response transactionTest(String name) {
    User user = userRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("NOT FOUND"));

    WalletDto.Response walletDto = walletClient.create(new WalletDto.Create(user.getId()))
            .getBody();
    return new UserDto.Response(user.getId(),
            user.getName(),
            walletDto.getId());
  }
}
