package com.travelbuddy.bookingservice.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long userId;
	
	private String origin;
	
	private String destination;
	
	private LocalDate travelDate;
	
	private String transportType;
	
	private double price;
}
