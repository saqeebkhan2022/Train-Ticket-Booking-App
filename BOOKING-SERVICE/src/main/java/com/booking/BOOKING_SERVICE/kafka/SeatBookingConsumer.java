package com.booking.BOOKING_SERVICE.kafka;

import com.booking.BOOKING_SERVICE.enums.BookingStatus;
import com.booking.BOOKING_SERVICE.model.Booking;
import com.booking.BOOKING_SERVICE.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SeatBookingConsumer {

    @Autowired
    private BookingRepository bookingRepository;


    @KafkaListener(topics = "seat-booked", groupId = "booking-service-group")
    public void processSeatBooking(String message) {
        String[] data = message.split(",");
        String userId = data[0];
        String seatNumber = data[1];

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setSeatNumber(seatNumber);
        booking.setStatus(BookingStatus.CONFIRMED);

        bookingRepository.save(booking);
        System.out.println("Booking confirmed for user " + userId + " on seat " + seatNumber);
    }

    @KafkaListener(topics = "seat-unavailable", groupId = "booking-service-group")
    public void processSeatUnavailable(String message) {
        System.out.println("Booking failed: " + message);
    }
}
