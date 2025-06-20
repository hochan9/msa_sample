package com.sample.dto;

import lombok.Getter;

/**
 * create on 2025. 6. 19. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>날씨.. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Getter
public class WeatherDto {

  /**
   * 날짜.
   */
  private String date;

  /**
   * 날씨.
   */
  private String weather;

  /**
   * 생성자.
   *
   * @param date    날짜
   * @param weather 날씨
   */
  public WeatherDto(String date, String weather) {
    this.date = date;
    this.weather = weather;
  }
}
