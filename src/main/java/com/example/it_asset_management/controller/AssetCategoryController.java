package com.example.it_asset_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.it_asset_management.entity.AssetCategory;
import com.example.it_asset_management.service.AssetCategoryService;

@RestController
@RequestMapping("/api/asset-categories")
public class AssetCategoryController {

	private final AssetCategoryService service;

	public AssetCategoryController(AssetCategoryService service) {
		this.service = service;
	}

	@GetMapping
	public List<AssetCategory> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public AssetCategory getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AssetCategory create(@RequestBody AssetCategory request) {
		return service.create(request.getName(), request.getDescription());
	}

	@PutMapping("/{id}")
	public AssetCategory update(@PathVariable Long id, @RequestBody AssetCategory request) {
		return service.update(id, request.getName(), request.getDescription());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
