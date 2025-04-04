package com.booking.BOOKING_SERVICE.kafka;

import com.booking.BOOKING_SERVICE.enums.BookingStatus;
import com.booking.BOOKING_SERVICE.model.Booking;
import com.booking.BOOKING_SERVICE.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Component
@EnableKafka
public class SeatBookingConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SeatBookingConsumer.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ObjectMapper objectMapper; // For JSON parsing

    // Listening for successful seat bookings
    @KafkaListener(topics = "seat-booked", groupId = "booking-service-group")
    public void processSeatBooking(String message) {
        try {
            // Assuming message is in JSON format, convert it to an object
            BookingMessage bookingMessage = objectMapper.readValue(message, BookingMessage.class);

            // Create a booking instance and set the details
            Booking booking = new Booking();
            booking.setBookingId(generateBookingId());
            booking.setUserId(bookingMessage.getUserId());
            booking.setSeatNumber(bookingMessage.getSeatNumber());
            booking.setTrainId(bookingMessage.getTrainId());
            booking.setCoachType(bookingMessage.getCoachType()); // Set CoachType
            booking.setStatus(BookingStatus.CONFIRMED);
            booking.setBoarding(bookingMessage.getBoarding());
            booking.setDeparture(bookingMessage.getDeparture());
            booking.setDateOfJourney(bookingMessage.getDateOfJourney());
            booking.setNumberOfPassengers(bookingMessage.getNumberOfPassengers());

            // Save the booking to the database
            bookingRepository.save(booking);

            logger.info("Booking confirmed for user {} on seat {}", bookingMessage.getUserId(), bookingMessage.getSeatNumber());

        } catch (Exception e) {
            logger.error("Error processing seat booking: {}", message, e);
        }
    }

    // Listening for seat unavailability events
    @KafkaListener(topics = "seat-unavailable", groupId = "booking-service-group")
    public void processSeatUnavailable(String message) {
        // Log or process seat unavailability message
        logger.warn("Booking failed: {}", message);
    }

    // Helper method to generate a booking ID
    private String generateBookingId() {
        return "BOOK-" + System.currentTimeMillis(); // This could be improved for uniqueness
    }
}
