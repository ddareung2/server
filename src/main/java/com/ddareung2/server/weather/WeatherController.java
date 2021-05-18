package com.ddareung2.server.weather;

import com.ddareung2.server.weather.finedust.FineDustItem;
import com.ddareung2.server.weather.temperature.TemperatureItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ddareung2.server.weather.finedust.FineDustService;
import com.ddareung2.server.weather.temperature.TemperatureService;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class WeatherController {

    private final TemperatureService temperatureService;
    private final FineDustService fineDustService;

    @GetMapping(value = "/temperature")
    public ResponseEntity<Object> getTemperatureInfo(WeatherParam weatherParam) {
        return ResponseEntity.ok(temperatureService.getTemperature(weatherParam));
    }

    @GetMapping(value = "/finedust")
    public ResponseEntity<Object> getFineDustParam(WeatherParam weatherParam){
        return ResponseEntity.ok(fineDustService.getFineDustInfo(weatherParam));
    }

    @GetMapping(value = "/weather")
    public ResponseEntity<Object> getWeatherInfo(WeatherParam weatherParam){
        List<TemperatureItem> temperatureItems = temperatureService.getTemperature(weatherParam);
        List<FineDustItem> fineDustItems = fineDustService.getFineDustInfo(weatherParam);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("temperatureItems", temperatureItems);
        responseMap.put("fineDustItems", fineDustItems);

        return ResponseEntity.ok(responseMap);
    }
}
