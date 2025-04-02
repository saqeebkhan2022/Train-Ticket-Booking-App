package com.booking.BOOKING_SERVICE.requests;

import com.booking.BOOKING_SERVICE.enums.BookingStatus;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BookingRequest {

    private String userId;
    @Column(nullable = false)
    private String trainId;
    private String CoachType;
    @Column(nullable = false)
    private String boarding;
    @Column(nullable = false)
    private String departure;
    @Column(nullable = false)
    private BookingStatus status;
}
