package com.ddareung2.server.weather;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WeatherService {
    private static final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService";
    private final WeatherParam weather;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<Object> getWeather(WeatherParam weatherParam) {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm"));
        int tmpTime = Integer.parseInt(currentTime);
        
        if(tmpTime >= 0 && tmpTime < 230) {
            currentDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            currentTime = "2300";
        } else if (tmpTime >= 230 && tmpTime < 530) {
            currentTime = "0200";
        } else if (tmpTime >= 530 && tmpTime < 830) {
            currentTime = "0500";
        } else if (tmpTime >= 830 && tmpTime < 1130) {
            currentTime = "0800";
        } else if (tmpTime >= 1130 && tmpTime < 1430) {
            currentTime = "1100";
        } else if (tmpTime >= 1430 && tmpTime < 1730) {
            currentTime = "1400";
        } else if (tmpTime >= 1730 && tmpTime < 2030) {
            currentTime = "1700";
        } else if (tmpTime >= 2030 && tmpTime < 2330) {
            currentTime = "2000";
        }

        weatherParam.setBaseDate(currentDate);
        weatherParam.setBaseTime(currentTime);
        weatherParam.setPageNo(1);
        weatherParam.setNumOfRows(11);
        weatherParam.setServiceKey(weather.getServiceKey());

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        WebClient wc = WebClient.builder().uriBuilderFactory(factory).baseUrl(BASE_URL).build();

        ResponseEntity<JSONObject> response = wc.get()
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
                .retrieve()
                .toEntity(JSONObject.class)
                .block();

        if(response.getStatusCode() == HttpStatus.OK ){
            HashMap responseData = (HashMap) response.getBody().get("response");
            HashMap body = (HashMap)responseData.get("body");
            HashMap items = (HashMap)body.get("items");
            List<HashMap> itemArray = (List<HashMap>) items.get("item");

            List<WeatherItem> weatherItems = new ArrayList<>();

            for (HashMap<String, String> item : itemArray) {
                WeatherItem weatherItem = new WeatherItem();
                weatherItem.setCategory(item.get("category"));
                weatherItem.setFcstValue(Double.parseDouble(item.get("fcstValue")));
                weatherItem.setFcstDate(item.get("fcstDate"));
                weatherItem.setFcstTime(item.get("fcstTime"));

                weatherItems.add(weatherItem);
            }
            return new ResponseEntity<>(weatherItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
