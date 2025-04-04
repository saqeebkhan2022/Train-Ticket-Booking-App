package com.booking.BOOKING_SERVICE.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainAvailabilityRequest {

    @JsonProperty("bookingId")
    private String bookingId;
    @JsonProperty("trainId")
    private String trainId;
    @JsonProperty("coachType")
    private String coachType;
    private String fromStationCode;
    private String toStationCode;
    private int numberOfPassengers;

}
