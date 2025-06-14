package com.travelbuddy.fareservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.travelbuddy.fareservice.dto.FareCalculationDTO;
import com.travelbuddy.fareservice.dto.FareDTO;
import com.travelbuddy.fareservice.model.Fare;
import com.travelbuddy.fareservice.service.FareService;

@RestController
@RequestMapping("/api/fares")
public class FareController {

	private final FareService fareService;
	
	@Autowired
	public FareController(FareService fareService) {
		this.fareService = fareService;
	}
	
	@PostMapping
	public ResponseEntity<Fare> addFare(@RequestBody FareDTO fareDTO) {
		Fare savedFare = fareService.addFare(fareDTO);
		return ResponseEntity.ok(savedFare);
	}
	
	@GetMapping
	public ResponseEntity<List<Fare>> getAllFares() {
		return ResponseEntity.ok(fareService.getAllFares());
	}
	
	@PostMapping("/calculate")
	public ResponseEntity<Double> calculateFare(@RequestBody FareCalculationDTO request) {
		double fare = fareService.calculateFare(request);
		return ResponseEntity.ok(fare);
	}
}
