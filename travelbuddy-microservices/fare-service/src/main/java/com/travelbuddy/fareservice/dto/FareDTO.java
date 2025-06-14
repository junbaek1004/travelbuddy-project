package com.travelbuddy.fareservice.dto;

import lombok.Data;

@Data
public class FareDTO {

	private String transportType;
	private double baseFare;
	private double perKmRate;
	private String origin;
	private String destination;
}
