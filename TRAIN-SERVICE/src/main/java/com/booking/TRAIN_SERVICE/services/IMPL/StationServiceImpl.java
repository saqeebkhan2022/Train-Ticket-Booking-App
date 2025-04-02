package com.booking.TRAIN_SERVICE.services.IMPL;

import com.booking.TRAIN_SERVICE.model.Station;
import com.booking.TRAIN_SERVICE.repository.StationRepository;
import com.booking.TRAIN_SERVICE.services.StationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;



    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    @Transactional
    public Station saveStation(Station station) {
        String stationId = UUID.randomUUID().toString();
        station.setId(stationId);
        return stationRepository.save(station);
    }

    @Override
    @Transactional
    public void deleteStation(String stationId) {
        stationRepository.deleteById(stationId);
    }
}
