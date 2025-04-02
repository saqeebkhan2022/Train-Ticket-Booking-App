package com.booking.TRAIN_SERVICE.repository;

import com.booking.TRAIN_SERVICE.model.TrainStationMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
    public interface TrainStationRepository extends JpaRepository<TrainStationMapping, String> {
    List<TrainStationMapping> findByTrainId(String trainId);
}
