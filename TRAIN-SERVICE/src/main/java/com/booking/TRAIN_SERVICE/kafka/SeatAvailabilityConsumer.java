package com.booking.TRAIN_SERVICE.kafka;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.model.Coach;
import com.booking.TRAIN_SERVICE.model.Seat;
import com.booking.TRAIN_SERVICE.repository.CoachRepository;
import com.booking.TRAIN_SERVICE.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Component
public class SeatAvailabilityConsumer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "SeatAvailabilityRequest", groupId = "train-service")
    public void consumeSeatAvailabilityRequest(String trainId) {
        // Logic to check seat availability (you can check your database or in-memory storage here)
        System.out.println("Received Seat Availability Request for Train ID: " + trainId);

        // Example response, assuming the seat is available
        String availabilityResponse = "Train ID " + trainId + " has available seats.";

        // Send the response back to booking service
        kafkaTemplate.send("seatAvailabilityResponse", availabilityResponse);
    }

}
