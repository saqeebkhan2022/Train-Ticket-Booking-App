package com.booking.TRAIN_SERVICE.services;

import com.booking.TRAIN_SERVICE.model.Train;
import com.booking.TRAIN_SERVICE.request.TrainSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrainService {
    Train getTrainByNumber(String trainNumber);
    List<Train> getTrainsByName(String trainName);
    List<Train> getAllTrains();
    Train saveTrain(Train train);
    void deleteTrain(String trainId);
    Page<Train> searchTrain(TrainSearchRequest trainSearchRequest, Pageable pageable);}
