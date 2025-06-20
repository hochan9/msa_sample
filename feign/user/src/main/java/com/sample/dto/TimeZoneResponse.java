/*
 * Created by Hochan Son on 2025. 6. 19.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 19.
 */

package com.sample.dto;

import lombok.Getter;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA.
 * create by IntelliJ IDEA.
 *
 * <p> 시간 관련 Response. </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Getter
public class TimeZoneResponse {

  /**
   * timezone.
   */
  private String timeZone;

  /**
   * 현재 지역 시간.
   */
  private String currentLocalTime;

  /**
   * utc offset.
   */
  private Offset currentUtcOffset;

  /**
   * ?.
   */
  private Offset standardUtcOffset;

  /**
   * ??.
   */
  private boolean hasDayLightSaving;

  /**
   * ??.
   */
  private boolean isDayLightSavingActive;

  /**
   * ???.
   */
  private Object dstInterval; // null 가능성이 있어 Object 또는 별도 클래스로

  /**
   * offset.
   */
  @Getter
  public static class Offset {

    /**
     * 초.
     */
    private long seconds;

    /**
     * 밀리세컨.
     */
    private long milliseconds;

    /**
     * ?.
     */
    private long ticks;

    /**
     * ??.
     */
    private long nanoseconds;
  }
}