package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.HelloService;

@RestController
class RetryController {

	private final HelloService helloService;

	public RetryController(HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/retry")
	public String retryHello() {
		return helloService.getHelloWithRetry();
	}
}