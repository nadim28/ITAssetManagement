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

import com.example.it_asset_management.dto.AssetCategoryRequest;
import com.example.it_asset_management.dto.AssetCategoryResponse;
import com.example.it_asset_management.service.AssetCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/asset-categories")
public class AssetCategoryController {

	private final AssetCategoryService service;

	public AssetCategoryController(AssetCategoryService service) {
		this.service = service;
	}

	@GetMapping
	public List<AssetCategoryResponse> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public AssetCategoryResponse getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AssetCategoryResponse create(@Valid @RequestBody AssetCategoryRequest request) {
		return service.create(request);
	}

	@PutMapping("/{id}")
	public AssetCategoryResponse update(@PathVariable Long id, @Valid @RequestBody AssetCategoryRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
