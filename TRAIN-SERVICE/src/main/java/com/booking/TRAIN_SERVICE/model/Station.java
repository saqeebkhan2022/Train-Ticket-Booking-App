package com.booking.TRAIN_SERVICE.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "stations")
public class Station {

    @Id
    private String id;
    @Column(nullable = false,unique = true)
    private String stationCode;
    @Column(nullable = false)
    private String stationName;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
}
