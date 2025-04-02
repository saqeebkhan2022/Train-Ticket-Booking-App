package com.booking.TRAIN_SERVICE.kafka;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.model.Seat;
import com.booking.TRAIN_SERVICE.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainConsumer {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "booking-requested", groupId = "train-service-group")
    public void processBookingRequest(String message) {
        String[] data = message.split(",");
        Long userId = Long.parseLong(data[0]);
        String coachType = data[1];
        String boarding = data[2];
        String destination = data[3];

        List<Seat> availableSeats = seatRepository.findAvailableSeatsByCoachType(CoachType.valueOf(coachType));

        if (availableSeats.isEmpty()) {
            kafkaTemplate.send("seat-unavailable", "User " + userId + " - No seat available.");
            return;
        }

        Seat selectedSeat = availableSeats.get(0);
        selectedSeat.setBooked(true);
        seatRepository.save(selectedSeat);

        kafkaTemplate.send("seat-booked", userId + "," + selectedSeat.getSeatNumber());
    }
}
