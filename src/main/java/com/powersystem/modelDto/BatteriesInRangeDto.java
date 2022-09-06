package com.powersystem.modelDto;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatteriesInRangeDto {
	
	private List<String> batteries;
	private double totalCapacity;
	private double avgCapacity;

}
