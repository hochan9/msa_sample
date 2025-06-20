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
import com.sample.dto.WeatherDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA.
 * create by IntelliJ IDEA.
 *
 * <p>날씨 기반 FeignClient. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@FeignClient(name = "weather", url = "https://f-api.github.io",
        configuration = FeignConfig.class)
public interface WeatherClient {

  /**
   * 날씨 조회.
   *
   * @return 날씨
   */
  @GetMapping("/f-api/weather.json")
  ResponseEntity<List<WeatherDto>> getWeather();
}
