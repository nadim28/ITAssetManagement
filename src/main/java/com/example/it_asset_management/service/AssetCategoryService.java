package com.example.it_asset_management.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.it_asset_management.entity.AssetCategory;
import com.example.it_asset_management.repository.AssetCategoryRepository;

@Service
public class AssetCategoryService {

	private final AssetCategoryRepository repository;

	public AssetCategoryService(AssetCategoryRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<AssetCategory> getAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public AssetCategory getById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Asset category not found: id=" + id));
	}

	@Transactional
	public AssetCategory create(String name, String description) {
		AssetCategory category = new AssetCategory();
		category.setName(name);
		category.setDescription(description);
		return repository.save(category);
	}

	@Transactional
	public AssetCategory update(Long id, String name, String description) {
		AssetCategory category = getById(id);
		category.setName(name);
		category.setDescription(description);
		return repository.save(category);
	}

	@Transactional
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new NoSuchElementException("Asset category not found: id=" + id);
		}
		repository.deleteById(id);
	}
}
