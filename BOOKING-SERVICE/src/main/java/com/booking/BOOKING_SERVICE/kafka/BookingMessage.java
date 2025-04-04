package com.booking.BOOKING_SERVICE.kafka;

import com.booking.BOOKING_SERVICE.enums.BookingStatus;
import com.booking.BOOKING_SERVICE.enums.CoachType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingMessage {


    private String bookingId;
    private String userId;
    private String seatNumber;
    private String trainId;
    private CoachType coachType;  // Enum type for CoachType
    private String boarding;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")  // Format for LocalDateTime
    private LocalDateTime departure; // Changed to LocalDateTime

    @JsonFormat(pattern = "yyyy-MM-dd")  // Format for LocalDate
    private LocalDate dateOfJourney; // Changed to LocalDate

    private int numberOfPassengers;
    private BookingStatus status;
}
