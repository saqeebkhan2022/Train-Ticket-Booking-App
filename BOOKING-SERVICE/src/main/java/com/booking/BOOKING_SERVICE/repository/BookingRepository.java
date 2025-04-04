package com.booking.BOOKING_SERVICE.repository;

import com.booking.BOOKING_SERVICE.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository  extends JpaRepository<Booking,String> {
    List<Booking> findByUserId(String userId);
}
