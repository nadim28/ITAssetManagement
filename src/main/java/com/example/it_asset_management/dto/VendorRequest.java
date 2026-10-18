package com.example.it_asset_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VendorRequest(

	@NotBlank(message = "Vendor name is required")
	@Size(max = 100, message = "Vendor name must not exceed 100 characters")
	String name,

	@Size(max = 100, message = "Contact person must not exceed 100 characters")
	String contactPerson,

	@Email(message = "Email must be a valid email address")
	@Size(max = 100, message = "Email must not exceed 100 characters")
	String email,

	@Pattern(regexp = "^\\+?[0-9()\\- ]{6,20}$", message = "Phone must be 6-20 characters of digits, spaces, dashes, parentheses, or a leading +")
	@Size(max = 30, message = "Phone must not exceed 30 characters")
	String phone,

	@Size(max = 255, message = "Address must not exceed 255 characters")
	String address
) {
}
