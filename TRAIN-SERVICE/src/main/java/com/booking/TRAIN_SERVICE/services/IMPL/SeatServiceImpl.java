package com.booking.TRAIN_SERVICE.services.IMPL;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.kafka.SeatProducer;
import com.booking.TRAIN_SERVICE.model.Coach;
import com.booking.TRAIN_SERVICE.model.Seat;
import com.booking.TRAIN_SERVICE.repository.CoachRepository;
import com.booking.TRAIN_SERVICE.repository.SeatRepository;
import com.booking.TRAIN_SERVICE.services.SeatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SeatServiceImpl implements SeatService {

//    private final SeatRepository seatRepository;
//    private final CoachRepository coachRepository;
//
//    @Override
//    public boolean isSeatAvailable(String seatId, boolean isTatkal) {
//        Optional<Seat> seatOptional = seatRepository.findById(seatId);
//        if (!seatOptional.isPresent()){
//            return false;
//        }
//        Seat seat = seatOptional.get();
//        if (isTatkal){
//            return seat.isTatkalAvailable() && !seat.isBooked();
//        }
//        return !seat.isBooked();
//    }
//
//    @Override
//    public void bookedSeat(String seatId) throws Exception {
//        Optional<Seat> seatOptional = seatRepository.findById(seatId);
//        if (!seatOptional.isPresent()){
//            throw  new Exception("Seat Not Found");
//        }
//        Seat seat = seatOptional.get();
//        if (seat.isBooked()){
//            throw  new Exception("Seat is Already Booked");
//        }
//        seat.setBooked(true);
//        seatRepository.save(seat);
//
//    }
//
//    @Override
//    public void freeSeat(String seatId) throws Exception {
//        Optional<Seat> seatOptional = seatRepository.findById(seatId);
//        if (!seatOptional.isPresent()){
//            throw  new Exception("Seat Not Found");
//        }
//        Seat seat = seatOptional.get();
//        if (seat.isBooked()){
//            throw  new Exception("Seat is Already Booked");
//        }
//        seat.setBooked(false);
//        seatRepository.save(seat);
//    }
//
//    @Override
//    public Long getAvailableSeats(CoachType coachType, boolean isTatkal) {
//        if (isTatkal){
//            return seatRepository.countByCoachTypeAndIsTatkalAvailableTrueAndIsBookedFalse(coachType);
//        }
//        return seatRepository.countByCoachTypeAndIsBookedFalse(coachType);
//    }
//
//
//
//    @Override
//    public Seat getSeatById(String seatId) {
//        return seatRepository.findById(seatId)
//                .orElseThrow(() -> new RuntimeException("Seat not found!"));
//    }
//
//
//
//    @Override
//    @Transactional
//    public Seat saveSeat(Seat seat) {
//        Coach coach = coachRepository.findById(seat.getCoach().getId()).orElseThrow(() ->
//                new RuntimeException("Coach not found with this coachId " + seat.getCoach().getId()));
//        String seatId = UUID.randomUUID().toString();
//        seat.setId(seatId);
//        seat.setCoach(coach);
//        return seatRepository.save(seat);
//    }
//
//    @Override
//    public void updateSeatAvailability(String seatNumber) {
//        Seat seat = seatRepository.findBySeatNumber(seatNumber);
//        if (seat != null) {
//            seat.setBooked(true);
//            seatRepository.save(seat);
//            System.out.println("Seat " + seatNumber + " marked as booked.");
//        }
//    }
//
//
//    @Override
//    @Transactional
//    public void deleteSeat(String seatId) {
//        seatRepository.deleteById(seatId);
//    }


    private final SeatRepository seatRepository;
    private final SeatProducer seatProducer;  // Kafka producer

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository, SeatProducer seatProducer) {
        this.seatRepository = seatRepository;
        this.seatProducer = seatProducer;
    }

    @Override
    public boolean isSeatAvailable(String seatId, boolean isTatkal) {
        Optional<Seat> seatOptional = seatRepository.findById(seatId);
        if (!seatOptional.isPresent()) {
            return false;
        }
        Seat seat = seatOptional.get();
        if (isTatkal) {
            return seat.isTatkalAvailable() && !seat.isBooked();
        }
        return !seat.isBooked();
    }

    @Override
    public void bookSeat(String seatId, boolean isTatkal) throws Exception {
        Optional<Seat> seatOptional = seatRepository.findById(seatId);
        if (!seatOptional.isPresent()) {
            throw new Exception("Seat Not Found");
        }
        Seat seat = seatOptional.get();

        if (seat.isBooked()) {
            throw new Exception("Seat is Already Booked");
        }

        seat.setBooked(true);
        seatRepository.save(seat);

        // Send Kafka message to Booking Service
        seatProducer.sendSeatBookedMessage(seatId);
    }

    @Override
    public void freeSeat(String seatId) throws Exception {
        Optional<Seat> seatOptional = seatRepository.findById(seatId);
        if (!seatOptional.isPresent()) {
            throw new Exception("Seat Not Found");
        }
        Seat seat = seatOptional.get();

        if (!seat.isBooked()) {
            throw new Exception("Seat is not booked yet");
        }

        seat.setBooked(false);
        seatRepository.save(seat);

        // Send Kafka message to Booking Service
        seatProducer.sendSeatFreedMessage(seatId);
    }

    @Override
    public Long getAvailableSeats(CoachType coachType, boolean isTatkal) {
        if (isTatkal) {
            return seatRepository.countByCoachTypeAndIsTatkalAvailableTrueAndIsBookedFalse(coachType);
        }
        return seatRepository.countByCoachTypeAndIsBookedFalse(coachType);
    }

    @Override
    public Seat getSeatById(String seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found!"));
    }

    @Override
    public Seat saveSeat(Seat seat) {
        // Ensure coach exists
        String seatId = seat.getId();  // Assuming Seat is already pre-defined with an ID.
        if (seatId == null || seatId.isEmpty()) {
            seatId = UUID.randomUUID().toString();  // Generate new ID if not present.
        }
        seat.setId(seatId);
        return seatRepository.save(seat);
    }

    @Override
    public void deleteSeat(String seatId) {
        seatRepository.deleteById(seatId);
    }

    @Override
    public void updateSeatAvailability(String seatNumber) {
        Seat seat = seatRepository.findBySeatNumber(seatNumber);
        if (seat != null) {
            seat.setBooked(true);
            seatRepository.save(seat);
            System.out.println("Seat " + seatNumber + " marked as booked.");
        }
    }
}
