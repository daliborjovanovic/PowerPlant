package com.powersystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.powersystem.model.Battery;

@DataJpaTest
public class BatteryRepositoryTest {
	
	@Autowired
	private BatteryRepository batteryRepository;
	
	
	Battery battery1 = new Battery(1, "BatteryTest2", 21000, 500.0);
	Battery battery2 = new Battery(2, "BatteryTest3", 22000, 600.0);
	Battery battery3 = new Battery(3, "BatteryTest4", 23000, 700.0);
	Battery battery4 = new Battery(4, "BatteryTest5", 24000, 800.0);
	
	@Test
	void saveAllTest() throws Exception {
		List<Battery> batteries = Arrays.asList(battery1, battery2, battery3, battery4);
		List<Battery> savedBatteries = batteryRepository.saveAll(batteries);
		
		assertEquals(4, savedBatteries.size());
	}
	
	
	
}
