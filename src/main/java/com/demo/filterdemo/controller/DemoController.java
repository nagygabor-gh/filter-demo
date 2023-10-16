package com.demo.filterdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/demo/ping")
	public String ping() {
		return "It works!";
	}

	@PostMapping("/demo/register")
	public String register() {
		return "OK";
	}

}
