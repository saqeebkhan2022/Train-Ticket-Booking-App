package com.booking.BOOKING_SERVICE.requests;

import com.booking.BOOKING_SERVICE.enums.BookingStatus;
import com.booking.BOOKING_SERVICE.enums.CoachType;
import com.booking.BOOKING_SERVICE.model.PassengerDetails;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BookingRequest {

    private String userId;
    private String trainId;
    private CoachType coachType;
    private String fromStation;                 // Starting station (From)
    private String toStation;
    private String boarding;
    private LocalDateTime departure;
    private int numberOfPassengers;
    private List<PassengerDetails> passengers;
}
