package com.booking.TRAIN_SERVICE.request;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainAvailabilityRequest {

    private String bookingId;
    private String trainId;
    private String fromStationCode;
    private String toStationCode;
    private int numberOfPassengers;
    private String coachType;

}
