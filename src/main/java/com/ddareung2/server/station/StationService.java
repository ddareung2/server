package com.ddareung2.server.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ddareung2.server.model.StationInformation;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StationService {

    private final StationRepository stationRepository;

    public List<StationInformation> findAll() {
        return stationRepository.findAll();
    }

}
