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
 * <p> 클래스 설명. </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Getter
public class TimeZoneResponse {

  private String timeZone;
  private String currentLocalTime;
  private Offset currentUtcOffset;
  private Offset standardUtcOffset;
  private boolean hasDayLightSaving;
  private boolean isDayLightSavingActive;
  private Object dstInterval; // null 가능성이 있어 Object 또는 별도 클래스로

  @Getter
  public static class Offset {
    private long seconds;
    private long milliseconds;
    private long ticks;
    private long nanoseconds;
  }
}