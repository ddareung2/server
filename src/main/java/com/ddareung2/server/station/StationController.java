package com.ddareung2.server.station;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@EnableScheduling
@RequiredArgsConstructor
@RestController
@RequestMapping("stations")
public class StationController {

    private final StationService stationService;

    @GetMapping()
    public ResponseEntity<List<StationInformation>> getStations() {
        List<StationInformation> stations = stationService.findAll();
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Optional<List<StationInformation>>> searchStations(@RequestParam("name") String name) {
        Optional<List<StationInformation>> stations = stationService.findByStationName(name);
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

	@Scheduled(fixedDelay = 210000)
    public void stationsBatch() {
        List<StationInformation> stations = stationService.getStationList();
        if(stations != null) {
            stationService.save(stations);
        }
    }
}
