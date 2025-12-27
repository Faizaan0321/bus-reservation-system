package com.example.Bus_reservation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bus_reservation.Service.BusService;
import com.example.Bus_reservation.entity.Bus;

@RestController
@RequestMapping("/Bus")
//@CrossOrigin(origins = "http://localhost:5173")
public class BusController {

	@Autowired
	private BusService busService;

	@PostMapping(value = "/AddBus")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public Bus AddBus(@RequestBody Bus bus) {
		return busService.addBus(bus);
	}

	@GetMapping(value = "/GetAllBusses")
//	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public List<Bus> GetAllBuses() {
		return busService.getAllBuses();
	}

	@GetMapping(value = "/GetBusById/{id}")
//	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public Bus GetBusById(@PathVariable(name = "id") Long id) {
		return busService.getBusById(id);
	}

	@GetMapping(value = "/SearchBus")
	public List<Bus> getBusBySourceAndDestination(@RequestParam String source,
			@RequestParam String destination) {		
		return busService.getbusbysourceanddestination(source, destination);
	}
	
	@DeleteMapping(value = "/deleteBusById/{id}")
	public String deleteBusById(@PathVariable(name = "id") Long id) {
		busService.deleteBusById(id);
		return "Bus Delete Successfully";
	}

}
