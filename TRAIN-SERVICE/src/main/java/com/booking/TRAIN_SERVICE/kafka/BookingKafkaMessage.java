package com.booking.TRAIN_SERVICE.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingKafkaMessage {
    private String bookingId;
    private String userId;
    private String trainId;
    private String coachType;
    private String boarding;
    private String departure;
    private String status;
}
