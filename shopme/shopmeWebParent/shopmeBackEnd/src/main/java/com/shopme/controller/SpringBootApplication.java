package com.shopme.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EntityScan(basePackages = { "com.shopme" })
@ComponentScan(basePackages = { "com.shopme" })
@EnableJpaRepositories("com.shopme.repo")
public class SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);

	}

}
