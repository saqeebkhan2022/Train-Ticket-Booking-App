package com.booking.TRAIN_SERVICE.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "train_stations")
public class TrainStationMapping {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "train_id",nullable = false)
    private Train train;
    @ManyToOne
    @JoinColumn(name = "station_id",nullable = false)
    private Station station;
    @Column(nullable = false)
    private int stopNumber;
    private List<String> runningOn;
    @Column(nullable = false)
    private LocalTime arrivalTime;
    @Column(nullable = false)
    private LocalTime departureTime;
}
