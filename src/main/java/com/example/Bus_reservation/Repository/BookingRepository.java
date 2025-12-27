package com.example.Bus_reservation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bus_reservation.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByUserId(Long userId);
}
