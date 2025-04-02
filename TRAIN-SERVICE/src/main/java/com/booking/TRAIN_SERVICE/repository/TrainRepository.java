package com.booking.TRAIN_SERVICE.repository;

import com.booking.TRAIN_SERVICE.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainRepository extends JpaRepository<Train, String> {
    Optional<Train> findByTrainNumber(String trainNumber);

    List<Train> findByTrainNameContainingIgnoreCase(String trainName);
}
