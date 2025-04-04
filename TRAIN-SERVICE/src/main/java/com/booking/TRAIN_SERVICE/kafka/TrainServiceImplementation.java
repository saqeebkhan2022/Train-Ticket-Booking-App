package com.booking.TRAIN_SERVICE.kafka;

import com.booking.TRAIN_SERVICE.enums.BookingStatus;
import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.model.Seat;
import com.booking.TRAIN_SERVICE.repository.SeatRepository;
import com.booking.TRAIN_SERVICE.request.BookingConfirmation;
import com.booking.TRAIN_SERVICE.request.TrainAvailabilityRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainServiceImplementation {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final SeatRepository seatRepository;

    private static final String TRAIN_BOOKING_TOPIC = "train-booking"; // The topic for booking confirmation

    // Kafka listener that listens to availability check request
    @KafkaListener(topics = "train-availability", groupId = "train-service")
    public void handleTrainAvailability(@Payload TrainAvailabilityRequest trainAvailabilityRequest) {
        // Convert the dateOfJourney to day of the week (Mon, Tue, Wed, etc.)

        // If the train runs on that day, check if there is an available seat
        Seat availableSeat = seatRepository.findAvailableSeat(
                trainAvailabilityRequest.getTrainId(),
                CoachType.valueOf(trainAvailabilityRequest.getCoachType()),
                false
        );

        if (availableSeat != null) {
            // Assign a seat and mark it as booked
            availableSeat.setBooked(true);
            availableSeat.setSeatNumber(generateRandomSeatNumber()); // Generate a random seat number

            // Save the updated seat (updating its booked status and seat number)
            seatRepository.save(availableSeat);

            // Create a booking confirmation to send back to the booking service
            BookingConfirmation bookingConfirmation = new BookingConfirmation(
                    trainAvailabilityRequest.getBookingId(),  // Include relevant details
                    availableSeat.getSeatNumber(),
                    BookingStatus.CONFIRMED
            );

            log.info("===================================>{}",bookingConfirmation);

            // Send the booking confirmation message to the booking service via Kafka
            kafkaTemplate.send(TRAIN_BOOKING_TOPIC, bookingConfirmation.toString());
        } else {
            // Handle the case when no seats are available
            System.out.println("No available seats for booking ID: " + trainAvailabilityRequest.getBookingId());
        }
    }




    private String generateRandomSeatNumber() {
        int seatNumber = (int) (Math.random() * 100) + 1; // Random seat number between 1 and 100
        return "S" + seatNumber;
    }


}

