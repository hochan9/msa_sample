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

import com.sample.dto.TimeZoneResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>시간 조회 FeignClient. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@FeignClient(name = "time", url = "https://www.timeapi.io")
public interface TimeClient {

  /**
   * TimeZone 의 시간을 반환.
   *
   * @param timeZone timezone
   * @return 시간 정보
   */
  @GetMapping("/api/timezone/zone")
  ResponseEntity<TimeZoneResponse> getTime(@RequestParam("timeZone") String timeZone);
}
