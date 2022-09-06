package com.powersystem.modelDto;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatteriesInRangeDto {
	
	private List<String> batteries;
	private double totalCapacity;
	private double avgCapacity;

}
