package com.example.it_asset_management.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.it_asset_management.entity.AssetStatus;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AssetRequest(

	@NotBlank(message = "Asset code is required")
	@Size(max = 30, message = "Asset code must not exceed 30 characters")
	String assetCode,

	@NotBlank(message = "Asset name is required")
	@Size(max = 100, message = "Asset name must not exceed 100 characters")
	String name,

	@NotNull(message = "Purchase date is required")
	LocalDate purchaseDate,

	@NotNull(message = "Purchase price is required")
	@DecimalMin(value = "0.00", message = "Purchase price must be zero or greater")
	BigDecimal purchasePrice,

	@NotNull(message = "Status is required")
	AssetStatus status,

	@NotNull(message = "Category is required")
	Long categoryId,

	@NotNull(message = "Vendor is required")
	Long vendorId
) {
}
