package com.example.Bus_reservation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bus_reservation.Exception.BusNotFoundException;
import com.example.Bus_reservation.Repository.BusRepository;
import com.example.Bus_reservation.entity.Bus;

@Service
public class BusService {

	@Autowired
	private BusRepository busRepository;

	public Bus addBus(Bus bus) {
		return busRepository.save(bus);
	}

	public List<Bus> getAllBuses() {
		return busRepository.findAll();
	}

	public Bus getBusById(Long id) {
		return busRepository.findById(id).orElseThrow(() -> new BusNotFoundException("Bus id not found"));
	}

	public List<Bus> getbusbysourceanddestination(String source, String Destination) {
		List<Bus> bus= busRepository.findBySourceAndDestination(source, Destination);
		 
		if(bus.isEmpty()) {
			throw new BusNotFoundException("Bus for this route not found");
		}else {
			return bus;
		}
	}
	
	public boolean deleteBusById(Long id) {
		Bus bus=busRepository.findById(id).orElseThrow(()->new BusNotFoundException("Given id bus not found"));	
	  if(bus==null) {		
		return false;
	  }else {
		  busRepository.deleteById(id);
			return true;
	  }
		
	}
		

}
