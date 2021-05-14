package com.ddareung2.server.weather.finedust;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FineDustItem {
    private double pm10Value;
    private int pm10Grade;
    private int pm10Grade1h;
    private double pm25Value;
    private int pm25Grade;
    private int pm25Grade1h;
    /*
     * Grade
     * 1 : 좋음
     * 2 : 보통
     * 3 : 나쁨
     * 4 : 매우나쁨
     */
}
