package com.powersystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.powersystem.model.Battery;
import com.powersystem.repository.BatteryRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class BatteryServiceTest {
	
	
    @Mock
    private BatteryRepository batteryRepository;
    private BatteryService batteryService;
    
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        batteryService = new BatteryService(batteryRepository);
    }
    
    @AfterEach
    void teardown() throws Exception {
        autoCloseable.close();
    }
	
	Battery battery1 = new Battery(1, "BatteryTest2", 21000, 500.0);
	Battery battery2 = new Battery(2, "BatteryTest3", 22000, 600.0);
	Battery battery3 = new Battery(3, "BatteryTest4", 23000, 700.0);
	Battery battery4 = new Battery(4, "BatteryTest5", 24000, 800.0);
	
	@Test
	void saveBaterriesTest() {
		List<Battery> batteries = Arrays.asList(battery1, battery2, battery3, battery4);
		
		batteryService.saveBatteries(batteries);
		verify(batteryRepository).saveAll(batteries);
		
	}
	
	
	@Test
	void batteriesInRangeTest() {
		int fromPostcode = anyInt();
		int toPostcode = anyInt();
		
		batteryService.batteriesInRange(fromPostcode, toPostcode);
		
		verify(batteryRepository).findAllByPostcodeBetweenOrderByNameAsc(fromPostcode, toPostcode);
	}
	
	
	@Test
	void totalCapacityTest() {
	  List<Battery> batteries = Arrays.asList(battery1, battery2, battery3, battery4);
	  
	  double totalCapacity = batteryService.totalCapacity(batteries);
	  
	  assertEquals(2600, totalCapacity);
		
	}
	

	@Test
	void avgCapacityTest() {
	  List<Battery> batteries = Arrays.asList(battery1, battery2, battery3, battery4);
	  
	  double avgCapacity = batteryService.avgCapacity(batteries);
	  
	  assertEquals(650, avgCapacity);
		
	}
	
	
	@Test
	void totalCapacity_when_ListOfBaterriesIsEmptyTest() {
		List<Battery> batteries = new ArrayList<>();
		
		double totalCapaciy = batteryService.totalCapacity(batteries);
		
		assertEquals(0.0, totalCapaciy);
	}
	

	@Test
	void avgCapacity_when_ListOfBaterriesIsEmptyTest() {
		List<Battery> batteries = new ArrayList<>();
		
		double avgCapaciy = batteryService.avgCapacity(batteries);
		
		assertEquals(0.0, avgCapaciy);
	}
	
	
}
