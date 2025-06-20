/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.repository;

import com.sample.entity.Wallet;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>R2dbcRepository 이용한 wallet Repository.. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public interface WalletRepository extends R2dbcRepository<Wallet, Long> {

  /**
   * userId 로 찾기.
   *
   * @param userId userId
   * @return 지갑
   */
  Mono<Wallet> findByUserId(UUID userId);

}
