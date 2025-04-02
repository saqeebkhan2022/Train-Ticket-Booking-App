package com.booking.BOOKING_SERVICE.services;

import com.booking.BOOKING_SERVICE.model.Booking;
import com.booking.BOOKING_SERVICE.requests.BookingRequest;

public interface BookingService {

    Booking createBooking(BookingRequest bookingRequest);
}
