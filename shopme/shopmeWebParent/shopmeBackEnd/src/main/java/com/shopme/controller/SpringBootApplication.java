package com.shopme.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EntityScan(basePackages = { "com.shopme" })
public class SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);

	}

}
