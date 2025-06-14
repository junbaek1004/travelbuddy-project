package com.travelbuddy.bookingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelbuddy.bookingservice.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByUserId(Long userId);
}
