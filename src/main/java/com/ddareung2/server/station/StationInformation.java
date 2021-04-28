package com.ddareung2.server.station;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Getter
@NoArgsConstructor
@Entity(name="station_information")
public class StationInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Column(name = "station_id", nullable = false)
	private String stationId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "latitude", nullable = false)
	private Double latitude;

	@Column(name = "longitude", nullable = false)
	private Double longitude;

	@Column(name = "total_rack_count", nullable = false)
	private Integer totalRackCount;

	@Column(name = "total_parking_bike_count", nullable = false)
	private Integer totalParkingBikeCount;

	@Builder
	public StationInformation(String stationId, String name, Double latitude, Double longitude,
			Integer totalRackCount, Integer totalParkingBikeCount) {
		this.stationId = stationId;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.totalRackCount = totalRackCount;
		this.totalParkingBikeCount = totalParkingBikeCount;
	}
}
