package com.booking.TRAIN_SERVICE.services.IMPL;

import com.booking.TRAIN_SERVICE.model.TrainStationMapping;
import com.booking.TRAIN_SERVICE.repository.TrainStationRepository;
import com.booking.TRAIN_SERVICE.services.TrainStationMappingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainStationMappingServiceImpl implements TrainStationMappingService {

    private final TrainStationRepository trainStationMappingRepository;


    @Override
    @Transactional
    public TrainStationMapping addTrainStationMapping(TrainStationMapping mapping) {
        String trainStationId = UUID.randomUUID().toString();
        mapping.setId(trainStationId);
        return trainStationMappingRepository.save(mapping);
    }

    @Override
    public List<TrainStationMapping> getAllMappings() {
        return trainStationMappingRepository.findAll();
    }

    @Override
    public List<TrainStationMapping> getMappingsByTrainId(String trainId) {
        return trainStationMappingRepository.findByTrainId(trainId);
    }

    @Override
    @Transactional
    public void deleteMapping(String id) {
        trainStationMappingRepository.deleteById(id);
    }
}
