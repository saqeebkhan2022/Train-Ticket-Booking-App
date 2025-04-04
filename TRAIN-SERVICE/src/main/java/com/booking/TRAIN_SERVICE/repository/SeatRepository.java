package com.booking.TRAIN_SERVICE.repository;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByCoachIdAndIsBookedFalse(String coachId);


    List<Seat> findAvailableSeatsByCoachId(CoachType coachType);




    List<Seat> findByCoachIdAndIsTatkalAvailableTrueAndIsBookedFalse(String coachId);

    @Query("SELECT COUNT(s) FROM Seat s WHERE s.coach.coachType=:coachType AND s.isTatkalAvailable = true AND s.isBooked = false")
    Long countByCoachTypeAndIsTatkalAvailableTrueAndIsBookedFalse(CoachType coachType);

    @Query("SELECT COUNT(s) FROM Seat s WHERE s.coach.coachType=:coachType AND  s.isBooked = false")
    Long countByCoachTypeAndIsBookedFalse(CoachType coachType);

    Seat findBySeatNumber(String seatNumber);

    @Query("SELECT s FROM Seat s WHERE s.isBooked = false AND s.coach.coachType = :coachType")
    List<Seat> findAvailableSeatsByCoachType(CoachType coachType);

    // Find an available seat based on trainId, coachType, and isBooked status

    @Query("SELECT s FROM Seat s " +
            "JOIN s.coach c " +
            "JOIN c.train t " +
            "WHERE t.id = :trainId " +  // Filter by trainId
            "AND c.coachType = :coachType " +  // Filter by coachType
            "AND s.isBooked = :isBooked")  // Filter by booking status
    Seat findAvailableSeat(
            @Param("trainId") String trainId,
            @Param("coachType") CoachType coachType,
            @Param("isBooked") boolean isBooked
    );


}
