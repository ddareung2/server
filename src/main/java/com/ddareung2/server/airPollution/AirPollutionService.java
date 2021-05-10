package com.ddareung2.server.airPollution;

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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AirPollutionService {
    private static final String BASE_URL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc";
    private final AirPollutionParam airPollution;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<Object> getAirPollutionInfo(AirPollutionParam airPollutionParam) {
        try {
            airPollutionParam.setPageNo(1);
            airPollutionParam.setNumOfRows(1);
            airPollutionParam.setServiceKey(airPollution.getServiceKey());
            String stationName = URLEncoder.encode(airPollutionParam.getStationName(), "UTF-8");
            airPollutionParam.setStationName(stationName);

            DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
            factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

            WebClient wc = WebClient.builder().uriBuilderFactory(factory).baseUrl(BASE_URL).build();
            ResponseEntity<JSONObject> response = wc.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/getMsrstnAcctoRltmMesureDnsty")
                            .queryParam("serviceKey", airPollutionParam.getServiceKey())
                            .queryParam("stationName", airPollutionParam.getStationName())
                            .queryParam("returnType", airPollutionParam.getReturnType())
                            .queryParam("pageNo", airPollutionParam.getPageNo())
                            .queryParam("numOfRows", airPollutionParam.getNumOfRows())
                            .queryParam("dataTerm", airPollutionParam.getDataTerm())
                            .queryParam("ver", airPollutionParam.getVer()).build()
                    ).headers(httpHeaders -> { httpHeaders.add("Content-Type", "application/json;charset=UTF-8");})
                    .accept(MediaType.APPLICATION_JSON)
                    .acceptCharset(StandardCharsets.UTF_8)
                    .retrieve()
                    .toEntity(JSONObject.class)
                    .block();

            if(response.getStatusCode() == HttpStatus.OK ){
                HashMap responseData = (HashMap) response.getBody().get("response");
                HashMap body = (HashMap)responseData.get("body");
                List<HashMap> itemArray = (List<HashMap>)body.get("items");

                List<AirPollutionItem> airPollutionItems = new ArrayList<>();

                for (HashMap<String, String> item : itemArray) {
                    AirPollutionItem airPollutionItem = new AirPollutionItem();
                    airPollutionItem.setPm10Value(Double.parseDouble(item.get("pm10Value")));
                    airPollutionItem.setPm10Grade(Integer.parseInt(item.get("pm10Grade")));
                    airPollutionItem.setPm10Grade1h(Integer.parseInt(item.get("pm10Grade1h")));
                    airPollutionItem.setPm25Value(Double.parseDouble(item.get("pm25Value")));
                    airPollutionItem.setPm25Grade(Integer.parseInt(item.get("pm25Grade")));
                    airPollutionItem.setPm25Grade1h(Integer.parseInt(item.get("pm25Grade1h")));
                    airPollutionItems.add(airPollutionItem);
                }
                return new ResponseEntity<>(airPollutionItems, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
