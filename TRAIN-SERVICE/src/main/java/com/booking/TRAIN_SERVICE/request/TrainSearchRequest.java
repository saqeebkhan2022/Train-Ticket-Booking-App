package com.booking.TRAIN_SERVICE.request;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.enums.SeatCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainSearchRequest implements Serializable {
     private String fromStationCode;
     private String toStationCode;
     private LocalDate dateOfJourney;
     private CoachType coachType;
     private SeatCategory seatCategory;
}
