/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.client;

import com.sample.common.config.FeignConfig;
import com.sample.wallet.client.WalletClient;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA.
 * create by IntelliJ IDEA.
 *
 * <p>wallet 기반 Feign Client. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@FeignClient(name = "wallet",
        url = "${api.url.wallet}",
        path = "/wallets",
        configuration = FeignConfig.class)
@Headers({"Content-type", "application/json"})
public interface WalletClientImpl extends WalletClient {
}
