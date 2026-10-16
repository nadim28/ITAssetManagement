package com.example.it_asset_management.service;

import com.example.it_asset_management.dto.PongResponse;
import org.springframework.stereotype.Service;

@Service
public class PingService {

	public PongResponse ping() {
		return new PongResponse("pong");
	}
}
