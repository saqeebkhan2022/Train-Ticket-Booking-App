package com.booking.BOOKING_SERVICE.controller;

import com.booking.BOOKING_SERVICE.model.Booking;
import com.booking.BOOKING_SERVICE.requests.BookingRequest;
import com.booking.BOOKING_SERVICE.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest booking){
        Booking serviceBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
