package com.ddareung2.server.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping()
    public ResponseEntity<?> getWeather(Weather weatherParam) {
        return weatherService.getWeather(weatherParam);
    }
}
