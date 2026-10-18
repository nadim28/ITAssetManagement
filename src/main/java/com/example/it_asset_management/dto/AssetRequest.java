package com.example.it_asset_management.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.it_asset_management.entity.AssetStatus;

public record AssetRequest(
	String assetCode,
	String name,
	LocalDate purchaseDate,
	BigDecimal purchasePrice,
	AssetStatus status,
	Long categoryId,
	Long vendorId
) {
}
