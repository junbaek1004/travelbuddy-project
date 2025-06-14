package com.travelbuddy.fareservice.service;

import java.util.List;

import com.travelbuddy.fareservice.dto.FareCalculationDTO;
import com.travelbuddy.fareservice.dto.FareDTO;
import com.travelbuddy.fareservice.model.Fare;

public interface FareService {

	Fare addFare(FareDTO fareDTO);
	List<Fare> getAllFares();
	double calculateFare(FareCalculationDTO requestDTO);
}
