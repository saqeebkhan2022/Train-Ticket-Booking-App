package com.booking.TRAIN_SERVICE.controller;

import com.booking.TRAIN_SERVICE.model.Coach;
import com.booking.TRAIN_SERVICE.services.CoachService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping
    public ResponseEntity<List<Coach>> getAllCoaches() {
        return ResponseEntity.ok(coachService.getAllCoaches());
    }

    @PostMapping
    public ResponseEntity<Coach> createCoach(@RequestBody Coach coach) {
        return ResponseEntity.ok(coachService.saveCoach(coach));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable String id) {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }
}
