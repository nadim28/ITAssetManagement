package com.example.it_asset_management.dto;

public record VendorResponse(
	Long id,
	String name,
	String contactPerson,
	String email,
	String phone,
	String address
) {
}
