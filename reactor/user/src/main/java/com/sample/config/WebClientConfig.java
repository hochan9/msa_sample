/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>Webclient 관련 Config. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class WebClientConfig {

  @Value("${api.url.wallet}")
  private String walletUrl;

  /**
   * 지갑 주소 Api webclient.
   *
   * @return Webcliet.
   */
  @Bean
  public WebClient walletWebClient() {
    return WebClient.builder()
        .baseUrl(walletUrl)
        .build();

  }
}
