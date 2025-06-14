package com.travelbuddy.fareservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelbuddy.fareservice.model.Fare;

@Repository
public interface FareRepository extends JpaRepository<Fare, Long> {

}
