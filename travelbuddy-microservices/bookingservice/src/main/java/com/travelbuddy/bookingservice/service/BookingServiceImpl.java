package com.travelbuddy.bookingservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.travelbuddy.bookingservice.dto.BookingDTO;
import com.travelbuddy.bookingservice.dto.FareCalculationDTO;
import com.travelbuddy.bookingservice.model.Booking;
import com.travelbuddy.bookingservice.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	
	private final BookingRepository bookingRepository;
	private final RestTemplate restTemplate;
	
	@Autowired
	public BookingServiceImpl(BookingRepository bookingRepository, RestTemplate restTemplate) {
		this.bookingRepository = bookingRepository;
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Booking createBooking(Long userId, BookingDTO bookingDTO) {
		
		FareCalculationDTO fareRequest = new FareCalculationDTO();
		fareRequest.setOrigin(bookingDTO.getOrigin());
		fareRequest.setDestination(bookingDTO.getDestination());
		fareRequest.setTransportType(bookingDTO.getTransportType());
		fareRequest.setDistance(bookingDTO.getDistance());
		
		Double calculatedFare = null;
		int attempts = 0;
		int maxAttempts = 5;

		while (attempts < maxAttempts) {
		    try {
		        calculatedFare = restTemplate.postForObject(fareServiceUrl, fareRequest, Double.class);
		        break; // success
		    } catch (Exception e) {
		        attempts++;
		        System.out.println("⚠️ Attempt " + attempts + " failed to reach fare-service. Retrying in 2s...");
		        try {
		            Thread.sleep(2000); // wait 2 seconds
		        } catch (InterruptedException ie) {
		            Thread.currentThread().interrupt();
		            throw new RuntimeException("Booking interrupted while waiting to retry fare-service", ie);
		        }
		    }
		}

		if (calculatedFare == null) {
		    throw new RuntimeException("❌ Could not connect to fare-service after retries");
		}

		
		Booking booking = new Booking();
		booking.setUserId(userId);
		booking.setOrigin(bookingDTO.getOrigin());
		booking.setDestination(bookingDTO.getDestination());
		booking.setTravelDate(bookingDTO.getTravelDate());
		booking.setTransportType(bookingDTO.getTransportType());
		booking.setPrice(calculatedFare);
		
		return bookingRepository.save(booking);
	}
	
	@Override
	public Booking getBookingById(Long id) {
		return bookingRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Booking not found"));
	}
	
	@Override
	public List<Booking> getBookingsByUser(Long userId) {
		return bookingRepository.findByUserId(userId);
	}
	
	@Override
	public Booking updateBooking(Long bookingId, BookingDTO bookingDTO, Long userId) {
		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("Booking Not Found"));
		
		if (booking.getUserId() == null || !userId.equals(booking.getUserId())) {
		    throw new RuntimeException("You can only update your own bookings");
		}
		
		booking.setOrigin(bookingDTO.getOrigin());
		booking.setDestination(bookingDTO.getDestination());
		booking.setTravelDate(bookingDTO.getTravelDate());
		booking.setTransportType(bookingDTO.getTransportType());
		booking.setPrice(bookingDTO.getPrice());
		
		FareCalculationDTO fareRequest = new FareCalculationDTO();
		fareRequest.setOrigin(bookingDTO.getOrigin());
		fareRequest.setDestination(bookingDTO.getDestination());
		fareRequest.setTransportType(bookingDTO.getTransportType());
		fareRequest.setDistance(bookingDTO.getDistance());
		
		Double newFare = restTemplate.postForObject(fareServiceUrl, fareRequest, Double.class);
		booking.setPrice(newFare);
		
		return bookingRepository.save(booking);
	}
	
	@Override
	public void cancelBooking(Long bookingId, Long userId) {
		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("Booking not found"));
		
		if (booking.getUserId() == null || !booking.getUserId().equals(userId)) {
		    throw new RuntimeException("You can only cancel your own bookings");
		}
		
		bookingRepository.delete(booking);
	}
	

	private final String fareServiceUrl = "http://fare-service:8083/api/fares/calculate";

	
}
