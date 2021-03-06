package com.ddareung2.server.station;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;;

@Getter
@NoArgsConstructor
@Entity(name="station_information")
public class StationInformation {
    @Id
    @Column(name = "station_id", nullable = false, updatable = false)
    private String stationId;

    @Column(name = "name", nullable = false)
    private String stationName;

    @Column(name = "latitude", nullable = false)
    private Double stationLatitude;

    @Column(name = "longitude", nullable = false)
    private Double stationLongitude;

    @Column(name = "total_rack_count", nullable = false)
    private Integer rackTotCnt;

    @Column(name = "total_parking_bike_count", nullable = false)
    private Integer parkingBikeTotCnt;

    @Column(name = "shared")
    private Integer shared;
    
    public StationInformation(String stationId) {
    	this.stationId = stationId;
    }

    @Builder
    public StationInformation(String stationId, String stationName, Double stationLatitude, Double stationLongitude,
                              Integer rackTotCnt, Integer parkingBikeTotCnt, Integer shared) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.stationLatitude = stationLatitude;
        this.stationLongitude = stationLongitude;
        this.rackTotCnt = rackTotCnt;
        this.parkingBikeTotCnt = parkingBikeTotCnt;
        this.shared = shared;
    }
}