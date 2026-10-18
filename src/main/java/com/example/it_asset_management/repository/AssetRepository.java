package com.example.it_asset_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.it_asset_management.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {

	boolean existsByAssetCodeIgnoreCaseAndIdNot(String assetCode, Long id);

	boolean existsByAssetCodeIgnoreCase(String assetCode);
}
