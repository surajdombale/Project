package com.shopme.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class Test {

	@GetMapping("/status")
	public String isServiceRunning() {
		return "Service is Running Successfully....";
	}
}