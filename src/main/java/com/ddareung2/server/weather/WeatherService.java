package com.ddareung2.server.weather;

import com.ddareung2.server.model.Weather;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class WeatherService {
    public final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<Object> getWeather(Weather weatherParam) {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm"));

        Weather weather = Weather.builder()
                .nx(weatherParam.getNx())
                .ny(weatherParam.getNy())
                .pageNo(1)
                .numOfRows(10)
                .baseDate(currentDate)
                .baseTime(currentTime)
                .build();

        System.out.println(weather);

        String result =  WebClient.create(BASE_URL)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getUltraSrtNcst")
                        .queryParam("ServiceKey", weather.getServiceKey())
                        .queryParam("dataType", weather.getDataType())
                        .queryParam("nx", weather.getNx())
                        .queryParam("ny", weather.getNy())
                        .queryParam("pageNo", weather.getPageNo())
                        .queryParam("numOfRows", weather.getNumOfRows())
                        .queryParam("base_date", weather.getBaseDate())
                        .queryParam("base_time", weather.getBaseTime()).build()
                ).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class).block();

        logger.debug(result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
