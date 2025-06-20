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

import com.sample.client.TimeClient;
import com.sample.client.WeatherClient;
import com.sample.dto.TimeZoneResponse;
import com.sample.dto.WeatherDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>테스트용. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {

  private final TimeClient timeClient;
  private final WeatherClient weatherClient;

  /**
   * 시간 조회.
   *
   * @return 시간.
   */
  @GetMapping("/time")
  public ResponseEntity<TimeZoneResponse> getTime() {
    return timeClient.getTime("Asia/Seoul");
  }

  /**
   * 날씨 조회.
   *
   * @return 조회
   */
  @GetMapping("/weather")
  public ResponseEntity<List<WeatherDto>> getWeather() {
    List<WeatherDto> weatherDtos = weatherClient.getWeather().getBody();
    return ResponseEntity.ok(weatherDtos);
  }
}
