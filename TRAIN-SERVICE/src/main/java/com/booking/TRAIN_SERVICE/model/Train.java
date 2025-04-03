package com.booking.TRAIN_SERVICE.model;

import com.booking.TRAIN_SERVICE.enums.TrainType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "trains")
public class Train implements Serializable {

    @Id
    private String id;
    @Column(nullable = false,unique = true)
    private String trainNumber;
    @Column(nullable = false)
    private String trainName;
    @Column(nullable = false)
    private int totalCoaches;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrainType trainType;
    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference("train")
    private List<Coach> coaches;
    @OneToMany(mappedBy = "train",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TrainStationMapping> trainStationMappings;

}
