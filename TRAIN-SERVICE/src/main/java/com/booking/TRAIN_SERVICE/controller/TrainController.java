package com.booking.TRAIN_SERVICE.controller;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.enums.SeatCategory;
import com.booking.TRAIN_SERVICE.model.Train;
import com.booking.TRAIN_SERVICE.request.TrainSearchRequest;
import com.booking.TRAIN_SERVICE.services.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
@RequiredArgsConstructor
@CrossOrigin(value = "*",allowCredentials = "false")
public class TrainController {

    private final TrainService trainService;

    @GetMapping("/{trainNumber}")
    public ResponseEntity<Train> getTrain(@PathVariable String trainNumber) {
        return ResponseEntity.ok(trainService.getTrainByNumber(trainNumber));
    }

//    @GetMapping("")
//    public ResponseEntity<List<Train>> searchTrains(@RequestParam String name) {
//        return ResponseEntity.ok(trainService.getTrainsByName(name));
//    }

    @GetMapping("/search")
    public Page<Train> searchTrains(
            @RequestParam String fromStationCode,
            @RequestParam String toStationCode,
            @RequestParam LocalDate dateOfJourney,
            @RequestParam CoachType coachType,
            @RequestParam SeatCategory seatCategory,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Create the TrainSearchRequest object and pass it to the service
        TrainSearchRequest searchRequest = new TrainSearchRequest(fromStationCode, toStationCode, dateOfJourney, coachType,seatCategory);

        Pageable pageable = PageRequest.of(page, size);
        return trainService.searchTrain(searchRequest, pageable);
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
