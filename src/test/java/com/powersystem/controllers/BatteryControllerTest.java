package com.powersystem.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powersystem.model.Battery;
import com.powersystem.modelDto.BatteryDto;
import com.powersystem.services.BatteryService;

public class BatteryControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ObjectMapper objectMapper;
	@MockBean
	BatteryService batteryService;

	Battery battery1 = new Battery(1, "BatteryTest2", 21000, 500.0);
	Battery battery2 = new Battery(2, "BatteryTest3", 22000, 600.0);
	Battery battery3 = new Battery(3, "BatteryTest4", 23000, 700.0);
	Battery battery4 = new Battery(4, "BatteryTest5", 24000, 800.0);

	@Test
	void addBatteries_success() throws Exception {
		List<Battery> batteries = Arrays.asList(battery1, battery2, battery3);
		List<Battery> batteriesWithNullIds = batteries.stream()
				.map(battery -> new Battery(0, battery.getName(), battery.getPostcode(), battery.getWattCapacity()))
				.collect(Collectors.toList());
		List<BatteryDto> batteriesDto = batteries.stream().map(battery -> modelMapper.map(battery, BatteryDto.class))
				.collect(Collectors.toList());

		Mockito.when(batteryService.saveBatteries(batteriesWithNullIds)).thenReturn(true);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/batteries")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(batteriesDto));

		mockMvc.perform(mockRequest).andExpect(status().isCreated()).andExpect(jsonPath("$[0].name").exists())
				.andExpect(jsonPath("$[0].postcode").exists()).andExpect(jsonPath("$[0].wattCapacity").exists())
				.andExpect(jsonPath("$[1].name").exists()).andExpect(jsonPath("$[1].postcode").exists())
				.andExpect(jsonPath("$[1].wattCapacity").exists()).andExpect(jsonPath("$[2].name").exists())
				.andExpect(jsonPath("$[2].postcode").exists()).andExpect(jsonPath("$[2].wattCapacity").exists())
				.andReturn();
	}

}
