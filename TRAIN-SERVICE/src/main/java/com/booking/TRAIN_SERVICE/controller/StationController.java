package com.booking.TRAIN_SERVICE.controller;

import com.booking.TRAIN_SERVICE.model.Station;
import com.booking.TRAIN_SERVICE.services.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public ResponseEntity<List<Station>> getAllStations() {
        return ResponseEntity.ok(stationService.getAllStations());
    }

    @PostMapping
    public ResponseEntity<Station> createStation(@RequestBody Station station) {
        return ResponseEntity.ok(stationService.saveStation(station));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable String id) {
        stationService.deleteStation(id);
        return ResponseEntity.noContent().build();
    }
}
