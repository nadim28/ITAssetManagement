package com.example.it_asset_management.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "assets")
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "asset_code", nullable = false, unique = true, length = 30)
	private String assetCode;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "purchase_date", nullable = false)
	private LocalDate purchaseDate;

	@Column(name = "purchase_price", nullable = false, precision = 12, scale = 2)
	private BigDecimal purchasePrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 30)
	private AssetStatus status;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private AssetCategory category;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id", nullable = false)
	private Vendor vendor;

	public Asset() {
	}

	public Long getId() {
		return id;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public AssetStatus getStatus() {
		return status;
	}

	public void setStatus(AssetStatus status) {
		this.status = status;
	}

	public AssetCategory getCategory() {
		return category;
	}

	public void setCategory(AssetCategory category) {
		this.category = category;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
}
