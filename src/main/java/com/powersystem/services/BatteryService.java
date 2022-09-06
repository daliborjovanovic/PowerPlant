package com.powersystem.services;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersystem.model.Battery;
import com.powersystem.repository.BatteryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BatteryService {

	@Autowired
	private BatteryRepository batteryRepository;

	public boolean saveBatteries(List<Battery> batteries) {
		try {
			batteryRepository.saveAll(batteries);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Battery> batteriesInRange(int from, int to) {
		return batteryRepository.findAllByPostcodeBetweenOrderByNameAsc(from, to);

	}
	

	public List<String> batteriesInRangeNames(List<Battery> batteries) {
		
		return batteries.stream()
						.map(battery -> battery.getName())
						.collect(Collectors.toList());

		
	}
	
	public double totalCapacity(List<Battery> batteries) {
		return batteries.stream()
							.map(battery->battery.getWattCapacity())
							.reduce(0.0, Double::sum);
	}
	
	public double avgCapacity(List<Battery> batteries) {
		return batteries.stream()
							.map(battery->battery.getWattCapacity())
							.collect(Collectors.summarizingDouble(Double::doubleValue))
							.getAverage();
	}
}
