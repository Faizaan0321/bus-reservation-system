package com.example.Bus_reservation.Exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpringExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException e) {

		Map<String, String> body = new HashMap<>();
		body.put("message", e.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BusNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleBusNotFountException(BusNotFoundException e) {

		Map<String, String> body = new HashMap<>();
		body.put("message", e.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleBookingNotFound(BookingNotFoundException e) {

		Map<String, String> body = new HashMap<>();
		body.put("message", e.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotEnoughSeatException.class)
	public ResponseEntity<Map<String, String>> handleNotEnoughSeatFound(NotEnoughSeatException e) {

		Map<String, String> body = new HashMap<>();
		body.put("message", e.getMessage());
		return new ResponseEntity<>(body, HttpStatus.IM_USED);
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<Map<String, String>> handleUserAreadyFoundException(UserAlreadyExistException e) {

		Map<String, String> body = new HashMap<>();
		body.put("message", e.getMessage());
		return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotvalidException(MethodArgumentNotValidException e) {

		List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();

		Map<String, String> map = new HashMap<String, String>();

		for (ObjectError objectError : objectErrors) {

			String field = ((FieldError) objectError).getField();

			map.put(field, objectError.getDefaultMessage());
		}

		e.printStackTrace();

		return new ResponseEntity(map, HttpStatus.NOT_FOUND);
	}

}
