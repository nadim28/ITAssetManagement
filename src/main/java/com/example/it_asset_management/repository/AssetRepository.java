package com.example.it_asset_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.it_asset_management.entity.Asset;
import com.example.it_asset_management.entity.AssetStatus;

public interface AssetRepository extends JpaRepository<Asset, Long> {

	boolean existsByAssetCodeIgnoreCaseAndIdNot(String assetCode, Long id);

	boolean existsByAssetCodeIgnoreCase(String assetCode);

	boolean existsByCategoryId(Long categoryId);

	boolean existsByVendorId(Long vendorId);

	@Query("""
		select a from Asset a
		where (cast(:keyword as string) is null
			or lower(a.assetCode) like lower(concat('%', cast(:keyword as string), '%'))
			or lower(a.name) like lower(concat('%', cast(:keyword as string), '%')))
		and (:categoryId is null or a.category.id = :categoryId)
		and (:status is null or a.status = :status)
		""")
	Page<Asset> search(@Param("keyword") String keyword,
			@Param("categoryId") Long categoryId,
			@Param("status") AssetStatus status,
			Pageable pageable);
}
