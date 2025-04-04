package com.booking.BOOKING_SERVICE.services;

import com.booking.BOOKING_SERVICE.enums.BookingStatus;
import com.booking.BOOKING_SERVICE.enums.CoachType;
import com.booking.BOOKING_SERVICE.model.Booking;
import com.booking.BOOKING_SERVICE.model.PassengerDetails;
import com.booking.BOOKING_SERVICE.requests.BookingRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    Booking createBooking(String userId, String trainId, CoachType coachType, String fromStation, String toStation, String boarding,
                          LocalDateTime departure, int numberOfPassengers, List<PassengerDetails> passengers);

    Booking getBookingById(String bookingId);

    Booking updateBookingStatus(String bookingId, BookingStatus status);

    void cancelBooking(String bookingId);

    List<Booking> getBookingsByUserId(String userId);
}
