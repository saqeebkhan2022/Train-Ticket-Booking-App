package com.booking.BOOKING_SERVICE.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;



@Component
@Slf4j
public class SeatBookingProducer {

    private static final String SEAT_BOOKED_TOPIC = "seat-booked";
    private static final String SEAT_UNAVAILABLE_TOPIC = "seat-unavailable";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;  // KafkaTemplate for sending messages

    @Autowired
    private ObjectMapper objectMapper;  // ObjectMapper to convert object to JSON

    // Method to send successful seat booking message
    public void sendSeatBookedMessage(BookingMessage bookingMessage) {
        try {
            // Convert the BookingMessage object to JSON
            String message = objectMapper.writeValueAsString(bookingMessage);
            kafkaTemplate.send(SEAT_BOOKED_TOPIC, message);
            log.info("Sent seat-booked message for booking ID: {}", bookingMessage.getBookingId());
        } catch (Exception e) {
            log.error("Error sending seat-booked message for booking ID: {}", bookingMessage.getBookingId(), e);
        }
    }

    // Method to send seat unavailability message
    public void sendSeatUnavailableMessage(String errorMessage) {
        try {
            // Send message to seat-unavailable topic
            kafkaTemplate.send(SEAT_UNAVAILABLE_TOPIC, errorMessage);
            log.warn("Sent seat-unavailable message: {}", errorMessage);
        } catch (Exception e) {
            log.error("Error sending seat-unavailable message: {}", errorMessage, e);
        }
    }
}
