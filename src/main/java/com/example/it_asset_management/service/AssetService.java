package com.example.it_asset_management.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.it_asset_management.dto.AssetRequest;
import com.example.it_asset_management.entity.Asset;
import com.example.it_asset_management.entity.AssetCategory;
import com.example.it_asset_management.entity.Vendor;
import com.example.it_asset_management.repository.AssetCategoryRepository;
import com.example.it_asset_management.repository.AssetRepository;
import com.example.it_asset_management.repository.VendorRepository;

@Service
public class AssetService {

	private final AssetRepository assetRepository;
	private final AssetCategoryRepository categoryRepository;
	private final VendorRepository vendorRepository;

	public AssetService(AssetRepository assetRepository,
			AssetCategoryRepository categoryRepository,
			VendorRepository vendorRepository) {
		this.assetRepository = assetRepository;
		this.categoryRepository = categoryRepository;
		this.vendorRepository = vendorRepository;
	}

	@Transactional(readOnly = true)
	public List<Asset> getAll() {
		return assetRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Asset getById(Long id) {
		return getEntityById(id);
	}

	@Transactional
	public Asset create(AssetRequest request) {
		AssetCategory category = resolveCategory(request.categoryId());
		Vendor vendor = resolveVendor(request.vendorId());

		Asset asset = new Asset();
		applyRequest(asset, request, category, vendor);
		return assetRepository.save(asset);
	}

	@Transactional
	public Asset update(Long id, AssetRequest request) {
		Asset asset = getEntityById(id);
		AssetCategory category = resolveCategory(request.categoryId());
		Vendor vendor = resolveVendor(request.vendorId());

		applyRequest(asset, request, category, vendor);
		return assetRepository.save(asset);
	}

	@Transactional
	public void delete(Long id) {
		if (!assetRepository.existsById(id)) {
			throw new NoSuchElementException("Asset not found: id=" + id);
		}
		assetRepository.deleteById(id);
	}

	private Asset getEntityById(Long id) {
		return assetRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Asset not found: id=" + id));
	}

	private AssetCategory resolveCategory(Long categoryId) {
		return categoryRepository.findById(categoryId)
			.orElseThrow(() -> new NoSuchElementException("Category not found: id=" + categoryId));
	}

	private Vendor resolveVendor(Long vendorId) {
		return vendorRepository.findById(vendorId)
			.orElseThrow(() -> new NoSuchElementException("Vendor not found: id=" + vendorId));
	}

	private static void applyRequest(Asset asset, AssetRequest request,
			AssetCategory category, Vendor vendor) {
		asset.setAssetCode(request.assetCode());
		asset.setName(request.name());
		asset.setPurchaseDate(request.purchaseDate());
		asset.setPurchasePrice(request.purchasePrice());
		asset.setStatus(request.status());
		asset.setCategory(category);
		asset.setVendor(vendor);
	}
}
