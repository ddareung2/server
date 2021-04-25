package com.ddareung2.server.station;

import com.ddareung2.server.model.StationInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("stations")
public class StationController {

    private final StationService stationService;

    @GetMapping()
    public ResponseEntity<List<StationInformation>> getAllStations() {
        List<StationInformation> stations = stationService.findAll();
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }
}
