package com.ddareung2.server.weather;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private final String serviceKey = "6QhJt2TrTftbPuSc98xTzMCNmpgtgKzi4bpewt0SFwMKQJdbMD8A4AIxH0XWd8%2FTypTiB%2ByVA0TD19qtj8wB4A%3D%3D";
    private final String dataType = "JSON";
    @NotEmpty
    private int nx;
    @NotEmpty
    private int ny;
    private String baseDate;
    private String baseTime;
    private int pageNo;
    private int numOfRows;

    private String resultCode;
    private String resultMessage;
    private int totalCount;
    private String category;
    private String obsrValue;
}
