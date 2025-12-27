package com.example.Bus_reservation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bus_reservation.Service.BookingService;
import com.example.Bus_reservation.dto.BookingRequest;
import com.example.Bus_reservation.entity.Booking;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/BookBus")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public Booking createBooking(@RequestBody BookingRequest request) {
		return bookingService.bookTicket(request.getUserId(), request.getBusId(), request.getSeatCount());
	}

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public List<Booking> getUserBookings(@PathVariable Long userId) {
		return bookingService.getBookingsForUser(userId);
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@DeleteMapping("/{bookingId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public String cancelBooking(@PathVariable Long bookingId) {
		bookingService.cancelBooking(bookingId);
		return "Booking cancelled successfully";
	}
}
