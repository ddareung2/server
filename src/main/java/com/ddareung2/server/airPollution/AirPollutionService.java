package com.ddareung2.server.airPolution;

import com.ddareung2.server.weather.WeatherParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AirPollutionService {
    private final String BASE_URL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc";
    private final WeatherParam weather;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


}
