package com.booking.BOOKING_SERVICE.kafka;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SeatAvailabilityProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void  sendSeatAvailabilityRequest(String trainId){
        kafkaTemplate.send("SeatAvailabilityRequest",trainId);
    }
}
