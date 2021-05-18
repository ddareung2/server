package com.ddareung2.server.weather.finedust;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
public class FineDustService {
    private static final String BASE_URL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc";
    private final FineDustParam fineDustParam;

    @SuppressWarnings("unchecked")
	public List<FineDustItem> getFineDustInfo(WeatherParam weatherParam) {
        List<FineDustItem> fineDustItems = new ArrayList<>();

        try {
            fineDustParam.setPageNo(1);
            fineDustParam.setNumOfRows(1);
            fineDustParam.setServiceKey(fineDustParam.getServiceKey());
            String stationName = URLEncoder.encode(weatherParam.getStationName(), "UTF-8");
            fineDustParam.setStationName(stationName);

            DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
            factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

            WebClient wc = WebClient.builder().uriBuilderFactory(factory).baseUrl(BASE_URL).build();
            ResponseEntity<JSONObject> response = wc.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/getMsrstnAcctoRltmMesureDnsty")
                            .queryParam("serviceKey", fineDustParam.getServiceKey())
                            .queryParam("stationName", fineDustParam.getStationName())
                            .queryParam("returnType", fineDustParam.getReturnType())
                            .queryParam("pageNo", fineDustParam.getPageNo())
                            .queryParam("numOfRows", fineDustParam.getNumOfRows())
                            .queryParam("dataTerm", fineDustParam.getDataTerm())
                            .queryParam("ver", fineDustParam.getVer()).build()
                    ).headers(httpHeaders -> httpHeaders.add("Content-Type", "application/json;charset=UTF-8"))
                    .accept(MediaType.APPLICATION_JSON)
                    .acceptCharset(StandardCharsets.UTF_8)
                    .retrieve()
                    .toEntity(JSONObject.class)
                    .block();

            if(response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
                Map<?, ?> responseData = response.getBody().get("response") != null ?
                		(Map<?, ?>) response.getBody().get("response") : new HashMap<>();
                
                Map<?, ?> body = responseData.get("body") != null ?
                		(Map<?, ?>)responseData.get("body") : new HashMap<>();
                
                
                List<HashMap<String, String>> itemArray = (List<HashMap<String, String>>)body.get("items");

                for (HashMap<String, String> item : itemArray) {
                    FineDustItem fineDustItem = new FineDustItem();
                    fineDustItem.setPm10Value(Double.parseDouble(item.get("pm10Value")));
                    fineDustItem.setPm10Grade(Integer.parseInt(item.get("pm10Grade")));
                    fineDustItem.setPm10Grade1h(Integer.parseInt(item.get("pm10Grade1h")));
                    fineDustItem.setPm25Value(Double.parseDouble(item.get("pm25Value")));
                    fineDustItem.setPm25Grade(Integer.parseInt(item.get("pm25Grade")));
                    fineDustItem.setPm25Grade1h(Integer.parseInt(item.get("pm25Grade1h")));
                    fineDustItems.add(fineDustItem);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return fineDustItems;
    }
}
