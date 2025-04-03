package com.booking.TRAIN_SERVICE.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "train_stations")
public class TrainStationMapping implements Serializable {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "train_id",nullable = false)
    @JsonBackReference
    private Train train;
    @ManyToOne
    @JoinColumn(name = "station_id",nullable = false)
    private Station station;
    @Column(nullable = false)
    private int stopNumber;
    @ElementCollection
    @CollectionTable(name = "train_running_days", joinColumns = @JoinColumn(name = "train_station_mapping_id"))
    @Column(name = "day_of_week")
    private List<String> runningOn;
    @Column(nullable = false)
    private LocalTime arrivalTime;
    @Column(nullable = false)
    private LocalTime departureTime;
}
