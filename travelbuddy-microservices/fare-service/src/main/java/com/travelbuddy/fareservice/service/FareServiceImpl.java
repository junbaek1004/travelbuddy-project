package com.travelbuddy.fareservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelbuddy.fareservice.dto.FareCalculationDTO;
import com.travelbuddy.fareservice.dto.FareDTO;
import com.travelbuddy.fareservice.model.Fare;
import com.travelbuddy.fareservice.repository.FareRepository;

@Service
public class FareServiceImpl implements FareService{
	
	private final FareRepository fareRepository;
	
	@Autowired
	public FareServiceImpl(FareRepository fareRepository) {
		this.fareRepository = fareRepository;
	}
	
	@Override
	public Fare addFare(FareDTO fareDTO) {
		Fare fare = new Fare();
		fare.setTransportType(fareDTO.getTransportType());
		fare.setBaseFare(fareDTO.getBaseFare());
		fare.setPerKmRate(fareDTO.getPerKmRate());
		fare.setOrigin(fareDTO.getOrigin());
		fare.setDestination(fareDTO.getDestination());
		return fareRepository.save(fare);
	}
	
	@Override
	public List<Fare> getAllFares() {
		return fareRepository.findAll();
	}
	
	@Override
	public double calculateFare(FareCalculationDTO request) {
		Optional<Fare> fare = fareRepository.findAll().stream()
				.filter(f -> f.getTransportType().equalsIgnoreCase(request.getTransportType())
				&& f.getOrigin().equalsIgnoreCase(request.getOrigin())
				&& f.getDestination().equalsIgnoreCase(request.getDestination()))
				.findFirst();
		
		if(fare.isEmpty()) {
			throw new RuntimeException("Fare rule not found for transport type: "+ request.getTransportType());
		}
		
		double base = fare.get().getBaseFare();
		double perKm = fare.get().getPerKmRate();
		double distance = request.getDistance();
		double rawFare = base + (perKm * distance);
		
		String type = request.getTransportType().toLowerCase();
		switch(type) {
		case "flight":
			rawFare *= 5;
			break;
		case "train":
			rawFare *= 3;
			break;
		case "bus":
			rawFare *=1.5;
			break;
		case "cab":
			rawFare *= 2;
			break;
		default:
			break;
			
		}
		
		return rawFare;
	}
	
}
