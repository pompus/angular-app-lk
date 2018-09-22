package com.leukemia.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leukemia.dao.HealthMeterDao;

@Service
public class HealthMeterService {

	@Autowired
	HealthMeterDao healthMeterDao;

	BigDecimal mul = new BigDecimal("100");

	public void addHealthMeter(Long healthMeter, String userId) {
		healthMeterDao.addHealthMeter(healthMeter, userId);
	}

	public Integer getOverAllHealthMeterPercentage() {
		Optional<BigDecimal> t = Optional.ofNullable(healthMeterDao.getOverAllHealthMeterPercentage());
		if (t.isPresent()) {
			return t.get().multiply(mul).intValue();
		}
		return 0;
	}

	public List<Integer> getHealthMeterForUser(String userId) {
		List<BigDecimal> list = healthMeterDao.getHealthMeterForUser(userId);
		List<Integer> newList = new ArrayList<>();
		list.stream().forEach(e -> {
			if (e == null)
				newList.add(0);
			else
				newList.add(e.multiply(mul).intValue());
		});
		return newList;
	}
}
