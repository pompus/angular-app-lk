package com.leukemia.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class HealthMeterDaoImpl implements HealthMeterDao {

	@Autowired
	JdbcTemplate jdbc;

	@Override
	public void addHealthMeter(Long healthMeter, String userId) {
		String sql = "update user_health set healthmeter=? where userid=?";
		jdbc.update(sql, healthMeter, userId);
	}

	@Override
	public BigDecimal getOverAllHealthMeterPercentage() {
		String sql = "select avg(healthmeter) from user_health where submission_date > sysdate -5";
		return jdbc.queryForObject(sql, BigDecimal.class);
	}

	@Override
	public List<BigDecimal> getHealthMeterForUser(String userId) {
		String sql = "select healthmeter from user_health where submission_date > sysdate -5 and userid=?";
		return jdbc.queryForList(sql, BigDecimal.class, userId);
	}
}
