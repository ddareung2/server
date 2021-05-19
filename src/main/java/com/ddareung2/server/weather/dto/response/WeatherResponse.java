package com.ddareung2.server.weather.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WeatherResponse {

    @NotNull
    private int fineDust;
    @NotNull
    private JSONObject weather;
}
