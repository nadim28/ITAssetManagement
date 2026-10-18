package com.example.it_asset_management.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.it_asset_management.dto.AssetCategoryRequest;
import com.example.it_asset_management.dto.AssetCategoryResponse;
import com.example.it_asset_management.entity.AssetCategory;
import com.example.it_asset_management.repository.AssetCategoryRepository;

@Service
public class AssetCategoryService {

	private final AssetCategoryRepository repository;

	public AssetCategoryService(AssetCategoryRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<AssetCategoryResponse> getAll() {
		return repository.findAll().stream()
			.map(AssetCategoryService::toResponse)
			.toList();
	}

	@Transactional(readOnly = true)
	public AssetCategoryResponse getById(Long id) {
		return toResponse(getEntityById(id));
	}

	@Transactional
	public AssetCategoryResponse create(AssetCategoryRequest request) {
		AssetCategory category = new AssetCategory();
		category.setName(request.name());
		category.setDescription(request.description());
		return toResponse(repository.save(category));
	}

	@Transactional
	public AssetCategoryResponse update(Long id, AssetCategoryRequest request) {
		AssetCategory category = getEntityById(id);
		category.setName(request.name());
		category.setDescription(request.description());
		return toResponse(repository.save(category));
	}

	@Transactional
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new NoSuchElementException("Asset category not found: id=" + id);
		}
		repository.deleteById(id);
	}

	private AssetCategory getEntityById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Asset category not found: id=" + id));
	}

	private static AssetCategoryResponse toResponse(AssetCategory category) {
		return new AssetCategoryResponse(category.getId(), category.getName(), category.getDescription());
	}
}
