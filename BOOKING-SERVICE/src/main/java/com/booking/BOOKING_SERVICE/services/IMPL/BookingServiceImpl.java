package com.booking.BOOKING_SERVICE.services.IMPL;

import com.booking.BOOKING_SERVICE.enums.BookingStatus;
import com.booking.BOOKING_SERVICE.enums.CoachType;
import com.booking.BOOKING_SERVICE.model.Booking;
import com.booking.BOOKING_SERVICE.model.PassengerDetails;
import com.booking.BOOKING_SERVICE.repository.BookingRepository;
import com.booking.BOOKING_SERVICE.requests.BookingRequest;
import com.booking.BOOKING_SERVICE.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String KAFKA_TOPIC = "bookings_topic";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Booking createBooking(String userId, String trainId, CoachType coachType, String fromStation, String toStation,
                                 String boarding, LocalDateTime departure, int numberOfPassengers, List<PassengerDetails> passengers) {

        Booking newBooking = Booking.builder()
                .bookingId(generateBookingId())
                .userId(userId)
                .trainId(trainId)
                .coachType(coachType)
                .fromStation(fromStation)
                .toStation(toStation)
                .boarding(boarding)
                .departure(departure)
                .numberOfPassengers(numberOfPassengers)
                .status(BookingStatus.WAITLIST)
                .dateOfJourney(LocalDate.now())
                .passengers(passengers)
                .build();

        Booking savedBooking = bookingRepository.save(newBooking);

        try {
            // Serialize the Booking object to JSON and send it to Kafka
            String bookingMessage = objectMapper.writeValueAsString(savedBooking);
            kafkaTemplate.send(KAFKA_TOPIC, bookingMessage);
        } catch (Exception e) {
            // Log error if there is an issue with Kafka communication
            throw new RuntimeException("Error sending booking data to Kafka", e);
        }

        return savedBooking;
    }

    @Override
    public Booking getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public Booking updateBookingStatus(String bookingId, BookingStatus status) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUserId(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    // Helper method to generate unique booking ID
    private String generateBookingId() {
        return "BOOK-" + System.currentTimeMillis();
    }
}
