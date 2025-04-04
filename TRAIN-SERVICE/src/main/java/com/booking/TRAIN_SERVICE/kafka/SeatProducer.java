package com.booking.TRAIN_SERVICE.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SeatProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public SeatProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Send a message when a seat is booked
    public void sendSeatBookedMessage(String seatId) {
        kafkaTemplate.send("seat-booking", "Seat booked: " + seatId);
    }

    // Send a message when a seat is freed
    public void sendSeatFreedMessage(String seatId) {
        kafkaTemplate.send("seat-booking", "Seat freed: " + seatId);
    }
}
