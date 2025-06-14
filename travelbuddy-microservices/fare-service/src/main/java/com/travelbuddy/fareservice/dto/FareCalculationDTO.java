package com.travelbuddy.fareservice.dto;

import lombok.Data;

@Data
public class FareCalculationDTO {

	private String origin;
	private String destination;
	private String transportType;
	private double distance;
	
}
