package com.ddareung2.server.weather;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class WeatherParam {
    @Value("${api.key.weather}")
    private String serviceKey;
    private final String dataType = "JSON";
    @NotEmpty
    private int nx;
    @NotEmpty
    private int ny;
    private String baseDate;
    private String baseTime;
    private int pageNo;
    private int numOfRows;
}