package com.booking.BOOKING_SERVICE.model;

import com.booking.BOOKING_SERVICE.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private String CoachType;
    @Column(nullable = false)
    private String boarding;
    private String seatNumber;
    @Column(nullable = false)
    private String departure;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
