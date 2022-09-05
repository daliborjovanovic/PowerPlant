package com.powersystem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.powersystem.mapper.BatteryMapper;
import com.powersystem.model.Battery;
import com.powersystem.modelDto.BatteriesInRangeDto;
import com.powersystem.modelDto.BatteryDto;
import com.powersystem.services.BatteryService;

@RestController
@RequestMapping
public class BatteryController {

	@Autowired
	private BatteryService batteryService;
	@Autowired
	private BatteryMapper batteryMapper;

	@PostMapping
	public ResponseEntity<Boolean> save(@RequestBody List<BatteryDto> batteriesDto) {
		List<Battery> batteries = new ArrayList<>();
		for (BatteryDto batteryDto : batteriesDto) {
			batteries.add(batteryMapper.mapDtoToEntity(batteryDto));
		}

		boolean ok = batteryService.saveBatteries(batteries);

		if (ok) {
			return new ResponseEntity<Boolean>(ok, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Boolean>(ok, HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping
	public ResponseEntity<BatteriesInRangeDto> postcodeRange(@RequestParam("from") int from, @RequestParam ("to") int to) {
		List<Battery> batteries= batteryService.batteriesInRange(from, to);
		List<String> batteriesName = batteryService.batteriesInRangeNames(batteries);
		double totalCapacity = batteryService.totalCapacity(batteries);
		double avgCapacity = batteryService.avgCapacity(batteries);
		
		return new ResponseEntity<BatteriesInRangeDto> (new BatteriesInRangeDto(batteriesName, totalCapacity, avgCapacity), HttpStatus.OK);
	}
}
