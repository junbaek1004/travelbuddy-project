package com.travelbuddy.bookingservice.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookingDTO {

	private String origin;
	private String destination;
	private LocalDate travelDate;
	private String transportType;
	private double price;
	private double distance;
}
