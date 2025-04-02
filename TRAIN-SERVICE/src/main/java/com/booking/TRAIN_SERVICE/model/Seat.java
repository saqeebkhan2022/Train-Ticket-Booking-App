package com.booking.TRAIN_SERVICE.model;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.enums.SeatCategory;
import com.booking.TRAIN_SERVICE.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "seats")
public class Seat {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "coach_id",nullable = false)
    @JsonBackReference
    private Coach coach;
    @Column(nullable = false)
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatCategory seatCategory;
    private double normalFare;
    private double tatkalFare;
    private double premiumTatkalFare;
    @Column(nullable = false)
    private boolean isBooked;
    private boolean isTatkalAvailable;
    @Version
    private int version;

    public Date getTatkalStartTime(){
        Calendar calendar = Calendar.getInstance();
        if (this.coach.getCoachType()== CoachType.FIRST_AC &&
                this.coach.getCoachType()== CoachType.SECOND_AC &&
                this.coach.getCoachType()== CoachType.THIRD_AC){
            calendar.set(Calendar.HOUR_OF_DAY,10);
        }else if (this.coach.getCoachType() == CoachType.SLEEPER){
            calendar.set(Calendar.HOUR_OF_DAY,11);
        }
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    public Date getTatkalEndTime(){
        Calendar calendar = Calendar.getInstance();
        if (this.coach.getCoachType()== CoachType.FIRST_AC &&
                this.coach.getCoachType()== CoachType.SECOND_AC &&
                this.coach.getCoachType()== CoachType.THIRD_AC){
            calendar.set(Calendar.HOUR_OF_DAY,11);
        }else if (this.coach.getCoachType() == CoachType.SLEEPER){
            calendar.set(Calendar.HOUR_OF_DAY,23);
        }
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }


}
