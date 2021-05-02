package com.ddareung2.server.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StationService {

    private final StationRepository stationRepository;

    public List<StationInformation> findAll() {
        return stationRepository.findAll();
    }

    public Optional<List<StationInformation>> findByName(String name) {
        return stationRepository.findByNameContaining(name);
    }

}
