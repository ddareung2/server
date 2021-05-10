package com.ddareung2.server.airPollution;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("airPollution")
public class AirPollutionController {

    private final AirPollutionService airPollutionService;

    @GetMapping()
    public ResponseEntity<Object> getAirPollutionInfo(AirPollutionParam airPollutionParam){
        return airPollutionService.getAirPollutionInfo(airPollutionParam);
    }
}
