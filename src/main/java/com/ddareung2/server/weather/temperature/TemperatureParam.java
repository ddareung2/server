package com.ddareung2.server.weather.temperature;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TemperatureParam {
    @Value("${api.key.weather}")
    private String serviceKey;
    private int pageNo;
    private int numOfRows;
    private final String dataType = "JSON";
    private String baseDate;
    private String baseTime;
    @NotBlank
    private int nx;
    @NotBlank
    private int ny;
}