package com.example.Bus_reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bus_reservation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	

}
