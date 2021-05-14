package com.ddareung2.server.weather;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddareung2.server.weather.finedust.FineDustParam;
import com.ddareung2.server.weather.finedust.FineDustService;
import com.ddareung2.server.weather.temperature.TemperatureParam;
import com.ddareung2.server.weather.temperature.TemperatureService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class WeatherController {

    private final TemperatureService weatherService;
    private final FineDustService airPollutionService;

    @GetMapping(value = "/temperature")
    public ResponseEntity<?> getWeather(TemperatureParam weatherParam) {
        return weatherService.getWeather(weatherParam);
    }

    @GetMapping(value = "/finedust")
    public ResponseEntity<Object> getAirPollutionInfo(FineDustParam airPollutionParam){
        return airPollutionService.getAirPollutionInfo(airPollutionParam);
    }
}
