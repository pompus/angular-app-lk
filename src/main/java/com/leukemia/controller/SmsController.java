package com.leukemia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leukemia.model.ChatResponse;
import com.leukemia.util.SendSms;

@RestController
public class SmsController {
	@RequestMapping(value = "/sos", method = RequestMethod.GET)
	public ResponseEntity<ChatResponse> getBotResponse(@RequestParam(name = "message") final String message,
			@RequestParam(name = "numbers") final String numbers) {
		SendSms sms=new SendSms();
		if (sms.sendSms(message, numbers))
		return new ResponseEntity<ChatResponse>(new ChatResponse("message sent succesfully"), HttpStatus.OK);
		else
			return new ResponseEntity<ChatResponse>(new ChatResponse("message sending failed"), HttpStatus.OK);
	}

}
