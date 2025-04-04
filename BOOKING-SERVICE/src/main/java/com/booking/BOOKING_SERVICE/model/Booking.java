package com.booking.BOOKING_SERVICE.model;

import com.booking.BOOKING_SERVICE.enums.BookingStatus;
import com.booking.BOOKING_SERVICE.enums.CoachType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "bookings")
public class Booking {
    @Id
    private String bookingId;
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String trainId;

    @Enumerated(EnumType.STRING)  // Ensure CoachType is an Enum
    @Column(nullable = false)
    private CoachType coachType;

    @Column(nullable = false)
    private String boarding; // Consider adding more validation or enum for station names

    @Column(nullable = false)
    private String fromStation;                 // Starting station (From)

    @Column(nullable = false)
    private String toStation;

    private String seatNumber; // This could be an optional field if applicable

    @Column(nullable = false)
    private LocalDateTime departure;  // Changed from String to LocalDateTime for proper date and time representation

    @Column(nullable = false)
    private int numberOfPassengers;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Column(nullable = false)
    private LocalDate dateOfJourney;

    @ElementCollection
    @CollectionTable(name = "passenger_details", joinColumns = @JoinColumn(name = "booking_id"))
    private List<PassengerDetails> passengers;

    @Column( updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Default value on creation

    @Version
    private int version;

}
