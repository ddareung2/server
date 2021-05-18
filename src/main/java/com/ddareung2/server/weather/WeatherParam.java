package com.ddareung2.server.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class WeatherParam {
    private String stationName;
    @NotEmpty
    private int nx;
    @NotEmpty
    private int ny;
}
