package com.booking.TRAIN_SERVICE.services.IMPL;

import com.booking.TRAIN_SERVICE.model.Train;
import com.booking.TRAIN_SERVICE.repository.TrainRepository;
import com.booking.TRAIN_SERVICE.services.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    @Override
    @Cacheable(value = "trains", key = "#trainNumber")
    public Train getTrainByNumber(String trainNumber) {
        return trainRepository.findByTrainNumber(trainNumber)
                .orElseThrow(() -> new RuntimeException("Train not found!"));
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train saveTrain(Train train) {
        String trainId = UUID.randomUUID().toString();
        train.setId(trainId);
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getTrainsByName(String trainName) {
         return trainRepository.findByTrainNameContainingIgnoreCase(trainName);
    }

    @Override
    public void deleteTrain(String trainId) {
        trainRepository.deleteById(trainId);
    }
}
