package com.booking.TRAIN_SERVICE.services;

import com.booking.TRAIN_SERVICE.model.Station;

import java.util.List;

public interface StationService {

    List<Station> getAllStations();
    Station saveStation(Station station);
    void deleteStation(String stationId);
}
