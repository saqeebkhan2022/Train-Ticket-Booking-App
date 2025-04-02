package com.booking.TRAIN_SERVICE.controller;

import com.booking.TRAIN_SERVICE.model.Train;
import com.booking.TRAIN_SERVICE.services.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;

    @GetMapping("/{trainNumber}")
    public ResponseEntity<Train> getTrain(@PathVariable String trainNumber) {
        return ResponseEntity.ok(trainService.getTrainByNumber(trainNumber));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Train>> searchTrains(@RequestParam String name) {
        return ResponseEntity.ok(trainService.getTrainsByName(name));
    }

    @GetMapping
    public ResponseEntity<List<Train>> getAllTrains() {
        return ResponseEntity.ok(trainService.getAllTrains());
    }

    @PostMapping
    public ResponseEntity<Train> createTrain(@RequestBody Train train) {
        return ResponseEntity.ok(trainService.saveTrain(train));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable String id) {
        trainService.deleteTrain(id);
        return ResponseEntity.noContent().build();
    }
}
