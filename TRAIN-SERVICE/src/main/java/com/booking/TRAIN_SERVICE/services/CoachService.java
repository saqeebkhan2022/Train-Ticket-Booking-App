package com.booking.TRAIN_SERVICE.services;

import com.booking.TRAIN_SERVICE.model.Coach;

import java.util.List;

public interface CoachService {
    List<Coach> getAllCoaches();
    Coach saveCoach(Coach coach);
    void deleteCoach(String coachId);
}
