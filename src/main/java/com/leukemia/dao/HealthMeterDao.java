package com.leukemia.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface HealthMeterDao {

	void addHealthMeter(Long healthMeter, String userId);

	BigDecimal getOverAllHealthMeterPercentage();

	List<BigDecimal> getHealthMeterForUser(String userId);
}
