package com.booking.TRAIN_SERVICE.services;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.model.Seat;

public interface SeatService {

//    boolean isSeatAvailable(String seatId,boolean isTatkal);
//    void bookedSeat(String seatId) throws  Exception;
//    void freeSeat (String seatId) throws  Exception;
//    Long getAvailableSeats(CoachType coachType,boolean isTatkal);
//    Seat getSeatById(String seatId);
//    Seat saveSeat(Seat seat);
//    void deleteSeat(String seatId);
//    void updateSeatAvailability(String seatNumber);

    boolean isSeatAvailable(String seatId, boolean isTatkal);
    void bookSeat(String seatId, boolean isTatkal) throws Exception;
    void freeSeat(String seatId) throws Exception;
    Long getAvailableSeats(CoachType coachType, boolean isTatkal);
    Seat getSeatById(String seatId);
    Seat saveSeat(Seat seat);
    void deleteSeat(String seatId);
    void updateSeatAvailability(String seatNumber);

}
