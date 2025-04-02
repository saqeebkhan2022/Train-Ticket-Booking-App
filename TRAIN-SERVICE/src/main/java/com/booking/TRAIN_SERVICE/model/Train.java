package com.booking.TRAIN_SERVICE.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "trains")
public class Train {

    @Id
    private String id;
    @Column(nullable = false,unique = true)
    private String trainNumber;
    @Column(nullable = false)
    private String trainName;
    @Column(nullable = false)
    private int totalCoaches;
    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Coach> coaches;




}
