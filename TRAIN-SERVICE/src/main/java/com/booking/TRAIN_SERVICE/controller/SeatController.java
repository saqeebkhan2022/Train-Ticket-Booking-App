package com.booking.TRAIN_SERVICE.controller;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.model.Seat;
import com.booking.TRAIN_SERVICE.services.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {


    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }


    @GetMapping("/available/count")
    public ResponseEntity<Long> getSeatsByCoach(@RequestParam CoachType coachType,boolean isTatkal) {
        Long availableSeats = seatService.getAvailableSeats(coachType, isTatkal);
        return ResponseEntity.ok(availableSeats);
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<Seat> getSeatById(@PathVariable String seatId) {
        return ResponseEntity.ok(seatService.getSeatById(seatId));
    }

    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.saveSeat(seat));
    }


    @DeleteMapping("/{seatId}")
    public ResponseEntity<Void> deleteSeat(@PathVariable String seatId) {
        seatService.deleteSeat(seatId);
        return ResponseEntity.noContent().build();
    }
}
