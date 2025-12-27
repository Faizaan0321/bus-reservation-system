package com.example.Bus_reservation.dto;

import lombok.Data;

@Data
public class BookingRequest {

	private Long userId;
	private Long busId;
	private int seatCount;

}
