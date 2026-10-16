package com.example.it_asset_management.controller;

import com.example.it_asset_management.dto.PongResponse;
import com.example.it_asset_management.service.PingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingController {

	private final PingService pingService;

	public PingController(PingService pingService) {
		this.pingService = pingService;
	}

	@GetMapping("/ping")
	public PongResponse ping() {
		return pingService.ping();
	}
}
