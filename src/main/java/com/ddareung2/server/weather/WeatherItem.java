package com.ddareung2.server.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherItem {
    private String category;
    private double fcstValue;
    private String fcstDate;
    private String fcstTime;
    /*
     * POP	강수확률	 %
     * PTY	강수형태	코드값
     * R06	6시간 강수량	범주 (1 mm)
     * REH	습도	 %
     * S06	6시간 신적설	범주(1 cm)
     * SKY	하늘상태	코드값
     * T3H	3시간 기온	 ℃
     * TMN	아침 최저기온	 ℃
     * TMX	낮 최고기온	 ℃
     * UUU	풍속(동서성분)	 m/s
     * VVV	풍속(남북성분)	 m/s
     */
}
