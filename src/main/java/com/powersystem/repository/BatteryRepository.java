package com.powersystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.powersystem.model.Battery;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {
	
	List<Battery> findAllByPostcodeBetweenOrderByNameAsc(int fromPostcode, int toPostcode);

}
