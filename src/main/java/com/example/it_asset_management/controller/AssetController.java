package com.example.it_asset_management.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.it_asset_management.entity.AssetStatus;

import com.example.it_asset_management.dto.AssetRequest;
import com.example.it_asset_management.dto.AssetResponse;
import com.example.it_asset_management.service.AssetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

	private final AssetService service;

	public AssetController(AssetService service) {
		this.service = service;
	}

	@GetMapping
	public Page<AssetResponse> getAll(
			@ParameterObject Pageable pageable,
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) Long categoryId,
			@RequestParam(required = false) AssetStatus status) {
		return service.getAll(pageable, keyword, categoryId, status);
	}

	@GetMapping("/{id}")
	public AssetResponse getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AssetResponse create(@Valid @RequestBody AssetRequest request) {
		return service.create(request);
	}

	@PutMapping("/{id}")
	public AssetResponse update(@PathVariable Long id, @Valid @RequestBody AssetRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
