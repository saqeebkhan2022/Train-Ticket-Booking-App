package com.booking.BOOKING_SERVICE.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SeatBookingProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendSeatBookingRequest( String userId,String trainId, String coachType, String boarding, String destination) {
        // Send the message to Kafka topic
        String bookingDetails = trainId + "," + userId + "," + coachType + "," +boarding + "," + destination;
        kafkaTemplate.send("BookingRequest", bookingDetails);
    }
}
