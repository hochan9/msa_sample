package com.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class WeatherDto {

    private String date;
    private String weather;


    public WeatherDto(String date, String weather) {
        this.date = date;
        this.weather = weather;
    }
}
