package com.ddareung2.server.airPolution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AirPollutionParam {
    @Value("${api.key.airPollution}")
    private String serviceKey;
    private final String returnType = "JSON";
    private String stationName;
    private int pageNo;
    private int numOfRows;
}
