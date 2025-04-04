package com.booking.TRAIN_SERVICE.request;

import com.booking.TRAIN_SERVICE.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingConfirmation {
    private String bookingId;       // Unique booking identifier
    private String seatNumber;      // Assigned seat number
    private BookingStatus status;
}
