package com.example.it_asset_management.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AssetStatus {
	AVAILABLE,
	ASSIGNED,
	UNDER_MAINTENANCE;

	@JsonCreator
	public static AssetStatus fromJson(String value) {
		for (AssetStatus status : values()) {
			if (status.name().equalsIgnoreCase(value)) {
				return status;
			}
		}
		throw new IllegalArgumentException(
			"Status must be one of: AVAILABLE, ASSIGNED, UNDER_MAINTENANCE — received: '" + value + "'");
	}
}
