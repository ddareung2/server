package com.ddareung2.server.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddareung2.server.model.StationInformation;

@Repository
public interface StationRepository extends JpaRepository<StationInformation, Long> { }
