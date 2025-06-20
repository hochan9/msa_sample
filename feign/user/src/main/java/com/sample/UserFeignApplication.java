/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>Application. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
public class UserFeignApplication {

  /**
   * main method.
   *
   * @param args argument.
   */
  public static void main(String[] args) {
    SpringApplication.run(UserFeignApplication.class, args);
  }
}
