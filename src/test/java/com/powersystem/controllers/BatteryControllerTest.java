package com.powersystem.controllers;









import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powersystem.model.Battery;
import com.powersystem.services.BatteryService;

@WebMvcTest(BatteryController.class)
public class BatteryControllerTest {
	
	@Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper mapper;
    
    @Autowired
    ModelMapper modelMapper;
    
    @MockBean
    BatteryService batteryService;
	
	 
	    
    Battery battery1 = new Battery(1, "BatteryTest2", 21000, 500.0);
	Battery battery2 = new Battery(2, "BatteryTest3", 22000, 600.0);
	Battery battery3 = new Battery(3, "BatteryTest4", 23000, 700.0);
	Battery battery4 = new Battery(4, "BatteryTest5", 24000, 800.0);
	
	
	
	

	    
	  
	  
}

