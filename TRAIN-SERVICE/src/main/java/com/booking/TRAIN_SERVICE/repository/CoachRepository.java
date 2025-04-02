package com.booking.TRAIN_SERVICE.repository;

import com.booking.TRAIN_SERVICE.model.Coach;
import com.booking.TRAIN_SERVICE.model.TrainStationMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoachRepository extends JpaRepository<Coach, String> {
}
