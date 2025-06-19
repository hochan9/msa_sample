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

import com.sample.config.FeignConfig;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;

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
@FeignClient(name = "wallet",
        url = "${api.url.wallet}",
        path = "/wallet",
        configuration = FeignConfig.class)
@Headers({"Content-type", "application/json"})
public interface WalletClientImpl extends WalletClient {
}
