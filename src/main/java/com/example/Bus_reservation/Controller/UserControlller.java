package com.example.Bus_reservation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bus_reservation.Service.UserService;
import com.example.Bus_reservation.entity.User;

@RestController
@RequestMapping(value = "/users")
public class UserControlller {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(value = "/registeruser")
	public User RegisterUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.registerUser(user);
	}

	@GetMapping(value = "/getUserById/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User GetUserById(@PathVariable(name = "id") Long id) {
		return userService.getUserById(id);
	}

	@GetMapping(value = "/GetAlluser")
	public List<User> GetAlluser(){
		return userService.GetAllUser();
	}
	
	@DeleteMapping(value = "/deleteUserByid/{id}")
	public String deleteUserbyid(@PathVariable(name = "id") Long id) {
	 boolean c=  userService.DeleteUserByid(id);
	  if(c) {
	 return "User deleted successfully";
	  }
	  return "user not found";
	}
	
}
