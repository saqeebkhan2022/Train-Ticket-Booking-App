package com.booking.TRAIN_SERVICE.services;


import com.booking.TRAIN_SERVICE.model.TrainStationMapping;

import java.util.List;

public interface TrainStationMappingService {

    TrainStationMapping addTrainStationMapping(TrainStationMapping mapping);
    List<TrainStationMapping> getAllMappings();
    List<TrainStationMapping> getMappingsByTrainId(String trainId);
    void deleteMapping(String id);
}
