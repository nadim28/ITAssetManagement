package com.example.it_asset_management.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
		LocalDateTime timestamp,
		int status,
		String error,
		String message,
		List<FieldError> fieldErrors,
		String path
) {

	public record FieldError(String field, String message) {
	}
}
