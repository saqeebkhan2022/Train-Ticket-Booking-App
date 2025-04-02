package com.booking.BOOKING_SERVICE.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SeatAvailabilityConsumer {

    @KafkaListener(topics = "seatAvailabilityResponse",groupId = "booking-service")
    public void  consumeSeatAvailabilityResponse(String response){
        System.out.println("Received Seat Availability Response: " + response);
    }
}
