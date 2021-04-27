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

@Service
public class WeatherService {
    public final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<Object> getWeather(Weather weatherParam) {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String currentTime = LocalTime.now().minusHours(1).format(DateTimeFormatter.ofPattern("HHmm"));

        weatherParam.setPageNo(1);
        weatherParam.setNumOfRows(10);
        weatherParam.setBaseDate(currentDate);
        weatherParam.setBaseTime(currentTime);

        String result = WebClient.create(BASE_URL)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getVilageFcst")
                        .queryParam("serviceKey", weatherParam.getServiceKey())
                        .queryParam("dataType", weatherParam.getDataType())
                        .queryParam("nx", weatherParam.getNx())
                        .queryParam("ny", weatherParam.getNy())
                        .queryParam("pageNo", weatherParam.getPageNo())
                        .queryParam("numOfRows", weatherParam.getNumOfRows())
                        .queryParam("base_date", weatherParam.getBaseDate())
                        .queryParam("base_time", weatherParam.getBaseTime()).build()
                ).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class).block();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
