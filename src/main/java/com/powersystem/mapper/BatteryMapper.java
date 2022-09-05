package com.powersystem.mapper;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.powersystem.model.Battery;
import com.powersystem.modelDto.BatteryDto;

@Component
public class BatteryMapper {
	
	
	public Battery mapDtoToEntity(BatteryDto baterryDto) {
		Battery battery = new Battery();
		battery.setName(baterryDto.getName());
		battery.setPostcode(baterryDto.getPostcode());
		battery.setWattCapacity(baterryDto.getWattCapacity());

		return battery;
		
 }

}
