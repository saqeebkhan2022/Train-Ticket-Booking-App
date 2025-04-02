package com.booking.BOOKING_SERVICE.repository;

import com.booking.BOOKING_SERVICE.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository  extends JpaRepository<Booking,String> {
}
