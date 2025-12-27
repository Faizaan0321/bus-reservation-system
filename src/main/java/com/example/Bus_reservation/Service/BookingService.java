package com.example.Bus_reservation.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bus_reservation.Exception.BookingNotFoundException;
import com.example.Bus_reservation.Exception.BusNotFoundException;
import com.example.Bus_reservation.Exception.NotEnoughSeatException;
import com.example.Bus_reservation.Exception.UserNotFoundException;
import com.example.Bus_reservation.Repository.BookingRepository;
import com.example.Bus_reservation.Repository.BusRepository;
import com.example.Bus_reservation.Repository.UserRepository;
import com.example.Bus_reservation.entity.Booking;
import com.example.Bus_reservation.entity.Bus;
import com.example.Bus_reservation.entity.User;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking bookTicket(Long userId, Long busId, int seatCount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Bus bus = busRepository.findById(busId).orElseThrow(() -> new BusNotFoundException("Bus not found"));

    
        if (bus.getAvailableSeats() < seatCount) {
            throw new NotEnoughSeatException("Not enough seats available");
        }

       
        bus.setAvailableSeats(bus.getAvailableSeats() - seatCount);
        busRepository.save(bus);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBus(bus);
        booking.setSeatCount(seatCount);
        booking.setBookingDate(LocalDate.now());

        return bookingRepository.save(booking);
    }
  
    public List<Booking> getAllBookings(){
    	
    	return bookingRepository.findAll();
    }

    public List<Booking> getBookingsForUser(Long userId) {
    	
        return bookingRepository.findByUserId(userId);
    }
    
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        Bus bus = booking.getBus();
        bus.setAvailableSeats(bus.getAvailableSeats() + booking.getSeatCount());
        busRepository.save(bus);
        bookingRepository.delete(booking);
    }
    
    
}

	
	


