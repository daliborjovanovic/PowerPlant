package com.powersystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.powersystem.model.Battery;

@DataJpaTest
class BatteryRepositoryTest {
	
	@Autowired
	private BatteryRepository batteryRepository;
	
	
	Battery battery1 = new Battery(1, "BatteryTest1", 21000, 500.0);
	Battery battery2 = new Battery(2, "BatteryTest2", 22000, 600.0);
	Battery battery3 = new Battery(3, "BatteryTest3", 23000, 700.0);
	Battery battery4 = new Battery(4, "BatteryTest4", 24000, 800.0);
	
	@Test
	void saveAllTest() throws Exception {
		List<Battery> batteries = Arrays.asList(battery1, battery2, battery3, battery4);
		List<Battery> savedBatteries = batteryRepository.saveAll(batteries);
		
		assertEquals(4, savedBatteries.size());
	}
	
	
	@Test
	void findAllByPostCodeBetweenOrderByNameAsc() {
		List<Battery> batteries = Arrays.asList(battery1, battery2, battery3, battery4);
		batteryRepository.saveAll(batteries);
		
		int fromPostcode = 22000;
		int toPostcode = 24000;
		
		List<Battery> batteriesInRange = batteryRepository.findAllByPostcodeBetweenOrderByNameAsc(fromPostcode, toPostcode);
		System.out.print(batteriesInRange.size());
		
		assertEquals(3, batteriesInRange.size());
		assertEquals(2, batteriesInRange.get(0).getId());
		assertEquals(3, batteriesInRange.get(1).getId());
		assertEquals(4, batteriesInRange.get(2).getId());
	}
	
	
}
