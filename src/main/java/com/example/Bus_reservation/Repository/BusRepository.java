package com.example.Bus_reservation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bus_reservation.entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {

	 List<Bus> findBySourceAndDestination(String Source, String Destination);
	
}
