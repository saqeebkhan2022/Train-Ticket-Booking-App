package com.booking.TRAIN_SERVICE.model;


import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.enums.SeatCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "coaches")
public class Coach implements Serializable {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "train_id",nullable = false)
    @JsonBackReference("train")
    private Train train;
    @Column(nullable = false)
    private String coachNumber;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CoachType coachType;
    private int totalAvailableNormal;
    private int totalAvailableTatkal;
    @OneToMany(mappedBy = "coach",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Seat> seats;
    public  long getAvailableSeats(){
        return seats.stream().filter(seat -> !seat.isBooked()).count();
    }

    public int getGeneralWaitlistCount() {
        return seats.stream()
                .filter(seat -> seat.getSeatCategory() == SeatCategory.GENERAL) // Filter for General category
                .mapToInt(seat -> seat.getWaitlistForCategory(SeatCategory.GENERAL)) // Get General WL count for each seat
                .sum();
    }

    // Method to get the total Tatkal waitlist count
    public int getTatkalWaitlistCount() {
        return seats.stream()
                .filter(seat -> seat.getSeatCategory() == SeatCategory.TATKAL) // Filter for Tatkal category
                .mapToInt(seat -> seat.getWaitlistForCategory(SeatCategory.TATKAL)) // Get Tatkal WL count for each seat
                .sum();
    }

}
