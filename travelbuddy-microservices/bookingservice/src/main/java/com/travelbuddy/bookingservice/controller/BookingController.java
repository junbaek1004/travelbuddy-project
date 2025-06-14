package com.travelbuddy.bookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelbuddy.bookingservice.dto.BookingDTO;
import com.travelbuddy.bookingservice.model.Booking;
import com.travelbuddy.bookingservice.service.BookingService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/booking")
public class BookingController {

	private final BookingService bookingService;
	
	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@PostMapping
	public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO, HttpServletRequest request) {
		Long userId = (Long) request.getAttribute("userId");
		Booking booking = bookingService.createBooking(userId, bookingDTO);
		return ResponseEntity.ok(booking);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
		Booking booking = bookingService.getBookingById(id);
		return ResponseEntity.ok(booking);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Booking>> getBookingsByUser(HttpServletRequest request) {
		Long userId = (Long) request.getAttribute("userId");
		List<Booking> bookings = bookingService.getBookingsByUser(userId);
		return ResponseEntity.ok(bookings);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO, HttpServletRequest request) {
		Long userId = (Long) request.getAttribute("userId");
		Booking updated = bookingService.updateBooking(id, bookingDTO, userId);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> cancelBooking(@PathVariable Long id, HttpServletRequest request) {
		Long userId = (Long) request.getAttribute("userId");
		bookingService.cancelBooking(id, userId);
		return ResponseEntity.ok("Booking canceled successfully");
	}
	
	
}
