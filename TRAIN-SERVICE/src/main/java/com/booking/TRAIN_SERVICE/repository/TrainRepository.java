package com.booking.TRAIN_SERVICE.repository;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.enums.TrainType;
import com.booking.TRAIN_SERVICE.model.Train;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainRepository extends JpaRepository<Train, String> {
    Optional<Train> findByTrainNumber(String trainNumber);

    List<Train> findByTrainNameContainingIgnoreCase(String trainName);

//    @Query("SELECT t FROM Train t " +
//            "JOIN t.trainStationMappings ts " +
//            "JOIN ts.train.coaches c " +
//            "JOIN c.seats s " +
//            "JOIN ts.station s1 " +
//            "JOIN ts.runningOn trd " +
//            "WHERE s1.stationCode = :fromStationCode " +
//            "AND s1.stationCode = :toStationCode " +
//            "AND :dayOfWeek MEMBER OF ts.runningOn " +
//            "AND c.coachType = :coachType")
    @Query("SELECT t FROM Train t " +
            "JOIN t.trainStationMappings ts1 " +
            "JOIN ts1.station s1 " +
            "JOIN t.trainStationMappings ts2 " +
            "JOIN ts2.station s2 " +
            "JOIN t.coaches c " +
            "WHERE s1.stationCode = :fromStationCode " +   // Correct condition for from station
            "AND s2.stationCode = :toStationCode " +       // Correct condition for to station
            "AND :dayOfWeek MEMBER OF ts1.runningOn " +    // Check if day is in the runningOn list
            "AND c.coachType = :coachType")

    List<Train> findTrains(@Param("fromStationCode") String fromStationCode,
                           @Param("toStationCode") String toStationCode,
                           @Param("dayOfWeek") String dayOfWeek,
                           @Param("coachType") CoachType coachType);

}
