package com.booking.TRAIN_SERVICE.controller;

import com.booking.TRAIN_SERVICE.model.TrainStationMapping;
import com.booking.TRAIN_SERVICE.services.TrainStationMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/train-station")
@RequiredArgsConstructor
public class TrainStationMappingController {

    private final TrainStationMappingService trainStationMappingService;

    @PostMapping
    public ResponseEntity<TrainStationMapping> addMapping(@RequestBody TrainStationMapping mapping) {
        return ResponseEntity.ok(trainStationMappingService.addTrainStationMapping(mapping));
    }

    @GetMapping
    public ResponseEntity<List<TrainStationMapping>> getAllMappings() {
        return ResponseEntity.ok(trainStationMappingService.getAllMappings());
    }

    @GetMapping("/train/{trainId}")
    public ResponseEntity<List<TrainStationMapping>> getMappingsByTrain(@PathVariable String trainId) {
        return ResponseEntity.ok(trainStationMappingService.getMappingsByTrainId(trainId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMapping(@PathVariable String id) {
        trainStationMappingService.deleteMapping(id);
        return ResponseEntity.noContent().build();
    }
}
