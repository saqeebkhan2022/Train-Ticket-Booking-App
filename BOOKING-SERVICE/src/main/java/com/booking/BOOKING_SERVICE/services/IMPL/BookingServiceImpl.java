package com.booking.BOOKING_SERVICE.services.IMPL;

import com.booking.BOOKING_SERVICE.enums.BookingStatus;
import com.booking.BOOKING_SERVICE.model.Booking;
import com.booking.BOOKING_SERVICE.repository.BookingRepository;
import com.booking.BOOKING_SERVICE.requests.BookingRequest;
import com.booking.BOOKING_SERVICE.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public Booking createBooking(BookingRequest bookingRequest) {
        String bookingId = UUID.randomUUID().toString();
        Booking booking = Booking.builder()
                .bookingId(bookingId)
                .userId(bookingRequest.getUserId())
                .trainId(bookingRequest.getTrainId())
                .boarding(bookingRequest.getBoarding())
                .CoachType(bookingRequest.getCoachType())
                .departure(bookingRequest.getDeparture())
                .status(BookingStatus.CONFIRMED)
                .createdAt(LocalDateTime.now())
                .build();
        Booking savedBooking = bookingRepository.save(booking);
        kafkaTemplate.send("train-booking","Booking Created:" +savedBooking);
        return savedBooking;
    }
}
