package com.example.it_asset_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.it_asset_management.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
