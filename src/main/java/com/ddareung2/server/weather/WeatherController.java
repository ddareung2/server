package com.ddareung2.server.weather;

import com.ddareung2.server.weather.dto.request.WeatherRequest;
import com.ddareung2.server.weather.dto.response.WeatherResponse;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ddareung2.server.weather.finedust.FineDustService;
import com.ddareung2.server.weather.temperature.TemperatureService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class WeatherController {

    private final TemperatureService temperatureService;
    private final FineDustService fineDustService;

    @GetMapping(value = "/weather")
    public ResponseEntity<WeatherResponse> getWeatherInfo(WeatherRequest weatherRequest) {
        int fineDust = fineDustService.getFineDustInfo(weatherRequest);
        JSONObject weather = temperatureService.getTemperature(weatherRequest);

        WeatherResponse weatherResponse = new WeatherResponse();

        weatherResponse.setFineDust(fineDust);
        weatherResponse.setWeather(weather);

        return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
    }
}
