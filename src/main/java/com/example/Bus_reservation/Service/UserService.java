package com.example.Bus_reservation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bus_reservation.Exception.UserAlreadyExistException;
import com.example.Bus_reservation.Exception.UserNotFoundException;
import com.example.Bus_reservation.Repository.UserRepository;
import com.example.Bus_reservation.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User registerUser(User user) {
		if (userRepository.findByUsername(user.getUsername()) != null) {
			throw new UserAlreadyExistException("User already exists");
		}
		return userRepository.save(user);
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id not found"));
	}

	public List<User> GetAllUser() {
		return userRepository.findAll();
	}
	
	public boolean DeleteUserByid(Long id) {
		User user= userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id not found"));
		if(user!=null) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
