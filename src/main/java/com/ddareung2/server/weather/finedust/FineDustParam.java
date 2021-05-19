package com.ddareung2.server.weather.finedust;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class FineDustParam {
    @Value("${api.key.fineDust}")
    private String serviceKey;
    private final String returnType = "json";
    private final String dataTerm = "DAILY";
    private final String ver = "1.3";
    @NotBlank
    private String stationName;
    private int pageNo;
    private int numOfRows;
}
