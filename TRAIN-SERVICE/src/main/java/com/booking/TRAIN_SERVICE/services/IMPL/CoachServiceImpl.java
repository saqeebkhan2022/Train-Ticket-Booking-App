package com.booking.TRAIN_SERVICE.services.IMPL;

import com.booking.TRAIN_SERVICE.model.Coach;
import com.booking.TRAIN_SERVICE.model.Train;
import com.booking.TRAIN_SERVICE.repository.CoachRepository;
import com.booking.TRAIN_SERVICE.repository.TrainRepository;
import com.booking.TRAIN_SERVICE.services.CoachService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final TrainRepository trainRepository;

    @Override
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    @Override
    @Transactional
    public Coach saveCoach(Coach coach) {
        Train train = trainRepository.findById(coach.getTrain().getId()).orElseThrow(()->
                new RuntimeException("Train not found"));
        String coachId = UUID.randomUUID().toString();
        coach.setId(coachId);
        coach.setTrain(train);
        return coachRepository.save(coach);
    }

    @Override
    @Transactional
    public void deleteCoach(String coachId) {
        coachRepository.deleteById(coachId);
    }
}
