package com.example.Bus_reservation.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String busNumber;
	private String source;
	private String destination;
	private String departureTime;
	private int TotalSeats;
	private int availableSeats;

	@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
	 @JsonIgnore
	private List<Booking> bookings;

}
