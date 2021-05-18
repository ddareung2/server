package com.ddareung2.server.weather.temperature;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ddareung2.server.weather.WeatherParam;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TemperatureService {
    private static final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService";
    private final TemperatureParam temperatureParam;

    @SuppressWarnings("unchecked")
	public List<TemperatureItem> getTemperature(WeatherParam weatherParam) {
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

        temperatureParam.setBaseDate(currentDate);
        temperatureParam.setBaseTime(currentTime);
        temperatureParam.setPageNo(1);
        temperatureParam.setNumOfRows(11);
        temperatureParam.setServiceKey(temperatureParam.getServiceKey());

        List<TemperatureItem> temperatureItems = new ArrayList<>();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        WebClient wc = WebClient.builder().uriBuilderFactory(factory).baseUrl(BASE_URL).build();

        ResponseEntity<JSONObject> response = wc.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getVilageFcst")
                        .queryParam("serviceKey", temperatureParam.getServiceKey())
                        .queryParam("dataType", temperatureParam.getDataType())
                        .queryParam("nx", weatherParam.getNx())
                        .queryParam("ny", weatherParam.getNy())
                        .queryParam("pageNo", temperatureParam.getPageNo())
                        .queryParam("numOfRows", temperatureParam.getNumOfRows())
                        .queryParam("base_date", temperatureParam.getBaseDate())
                        .queryParam("base_time", temperatureParam.getBaseTime()).build()
                ).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(JSONObject.class)
                .block();

        if(response != null && response.getStatusCode() == HttpStatus.OK ){
        	Map<?, ?> responseData = response.getBody().get("response") != null ?
            		(Map<?, ?>) response.getBody().get("response") : new HashMap<>();
            
            Map<?, ?> body = responseData.get("body") != null ?
            		(Map<?, ?>)responseData.get("body") : new HashMap<>();
            
            HashMap<String, Object> items = (HashMap<String, Object>)body.get("items");
            List<HashMap<String, String>> itemArray = (List<HashMap<String, String>>) items.get("item");

            for (HashMap<String, String> item : itemArray) {
                TemperatureItem temperatureItem = new TemperatureItem();
                temperatureItem.setCategory(item.get("category"));
                temperatureItem.setFcstValue(Double.parseDouble(item.get("fcstValue")));
                temperatureItem.setFcstDate(item.get("fcstDate"));
                temperatureItem.setFcstTime(item.get("fcstTime"));

                temperatureItems.add(temperatureItem);
            }
            return temperatureItems;
        } else {
            return temperatureItems;
        }
    }
}
