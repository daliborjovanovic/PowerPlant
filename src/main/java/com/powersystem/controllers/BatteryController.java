package com.powersystem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.powersystem.model.Battery;
import com.powersystem.modelDto.BatteriesInRangeDto;
import com.powersystem.modelDto.BatteryDto;
import com.powersystem.services.BatteryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/batteries")
@AllArgsConstructor
public class BatteryController {

	@Autowired
	private BatteryService batteryService;

	private ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<Boolean> saveBatteries(@RequestBody List<BatteryDto> batteriesDto) {
		List<Battery> batteries = new ArrayList<>();
		for (BatteryDto batteryDto : batteriesDto) {
			batteries.add(modelMapper.map(batteryDto, Battery.class));
		}

		boolean ok = batteryService.saveBatteries(batteries);
		HttpStatus httpStatus = ok ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

		return new ResponseEntity<>(ok, httpStatus);

	}

	@GetMapping
	public ResponseEntity<BatteriesInRangeDto> postcodeRange(@RequestParam("from") int from,
			@RequestParam("to") int to) {
		List<Battery> batteries = batteryService.batteriesInRange(from, to);
		List<String> batteriesName = batteryService.batteriesInRangeNames(batteries);
		double totalCapacity = batteryService.totalCapacity(batteries);
		double avgCapacity = batteryService.avgCapacity(batteries);

		return new ResponseEntity<>(
				new BatteriesInRangeDto(batteriesName, totalCapacity, avgCapacity), HttpStatus.OK);
	}
}
