package com.vaish.SpringBootRestService.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@Autowired
	Greeting greeting;
	
	AtomicLong counter = new AtomicLong();

	//http://localhost:8081/greeting?name=vaish
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam (value="name")String name) {
		
		greeting.setId(counter.incrementAndGet());
		greeting.setContent("Welcome to Spring Boot "+name);
		
		return greeting;
	}
}
