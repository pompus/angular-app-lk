package com.leukemia.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leukemia.model.User;
import com.leukemia.services.HealthMeterService;

@RestController
public class HealthMeterController {

	@Autowired
	HealthMeterService healthMeterService;

	@RequestMapping(value = "healthmeter/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity addHealthMeter(@RequestBody User user) {
		healthMeterService.addHealthMeter(user.getHealthmeter(), user.getUserid());
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "healthmeter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Integer> getOverAllHealthMeterPercentage() {
		Integer i = healthMeterService.getOverAllHealthMeterPercentage();
		return new ResponseEntity<>(i, HttpStatus.OK);
	}

	@RequestMapping(value = "healthmeter/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Integer>> getHealthMeterForUser(@PathVariable("userid") String userId) {
		List<Integer> list = healthMeterService.getHealthMeterForUser(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
