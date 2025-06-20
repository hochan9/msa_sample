/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.router;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.sample.handler.WalletHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p> Wallet 관련 router 정보. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class WalletRouter {

  /**
   * Wallet Router.
   *
   * @param walletHandler handler.
   * @return RouterFunction
   */
  @Bean
  public RouterFunction<ServerResponse> routeWallet(WalletHandler walletHandler) {
    return route()
        .GET("/wallets/{id}", walletHandler::findById)
        .GET("/wallets", walletHandler::findByUserId)
        .POST("/wallets", walletHandler::create)
        .build();
  }

}
