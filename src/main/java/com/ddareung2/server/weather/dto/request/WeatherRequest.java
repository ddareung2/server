package com.ddareung2.server.weather.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class WeatherRequest {

    @NotEmpty
    private int nx;
    @NotEmpty
    private int ny;
    @NotEmpty
    private String stationName;
}
