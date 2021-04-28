package com.ddareung2.server.station;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<List<StationInformation>> stations = stationService.findByName(name);
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }
}
