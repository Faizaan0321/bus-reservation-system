package com.example.Bus_reservation.Exception;

public class NotEnoughSeatException extends RuntimeException {

	public NotEnoughSeatException(String msg){
		super(msg);
	}
	
}
