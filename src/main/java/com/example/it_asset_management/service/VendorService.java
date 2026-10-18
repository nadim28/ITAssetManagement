package com.example.it_asset_management.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.it_asset_management.dto.VendorRequest;
import com.example.it_asset_management.dto.VendorResponse;
import com.example.it_asset_management.entity.Vendor;
import com.example.it_asset_management.repository.VendorRepository;

@Service
public class VendorService {

	private final VendorRepository repository;

	public VendorService(VendorRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<VendorResponse> getAll() {
		return repository.findAll().stream()
			.map(VendorService::toResponse)
			.toList();
	}

	@Transactional(readOnly = true)
	public VendorResponse getById(Long id) {
		return toResponse(getEntityById(id));
	}

	@Transactional
	public VendorResponse create(VendorRequest request) {
		Vendor vendor = new Vendor();
		applyRequest(vendor, request);
		return toResponse(repository.save(vendor));
	}

	@Transactional
	public VendorResponse update(Long id, VendorRequest request) {
		Vendor vendor = getEntityById(id);
		applyRequest(vendor, request);
		return toResponse(repository.save(vendor));
	}

	@Transactional
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new NoSuchElementException("Vendor not found: id=" + id);
		}
		repository.deleteById(id);
	}

	private Vendor getEntityById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Vendor not found: id=" + id));
	}

	private static void applyRequest(Vendor vendor, VendorRequest request) {
		vendor.setName(request.name());
		vendor.setContactPerson(request.contactPerson());
		vendor.setEmail(request.email());
		vendor.setPhone(request.phone());
		vendor.setAddress(request.address());
	}

	private static VendorResponse toResponse(Vendor vendor) {
		return new VendorResponse(
			vendor.getId(),
			vendor.getName(),
			vendor.getContactPerson(),
			vendor.getEmail(),
			vendor.getPhone(),
			vendor.getAddress());
	}
}
