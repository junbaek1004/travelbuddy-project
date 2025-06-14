package com.travelbuddy.bookingservice.service;

import java.util.List;

import com.travelbuddy.bookingservice.dto.BookingDTO;
import com.travelbuddy.bookingservice.model.Booking;

public interface BookingService {
	
	Booking createBooking(Long userId, BookingDTO bookingDTO);
	
	Booking getBookingById(Long id);
	
	List<Booking> getBookingsByUser(Long userId);
	
	Booking updateBooking(Long bookingId, BookingDTO bookingDto, Long userId);
	
	void cancelBooking(Long bookingId, Long userId);

}
