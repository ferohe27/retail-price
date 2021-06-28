package com.vass.retail_price.pvp.infrastructure.repository;

import com.vass.retail_price.pvp.infrastructure.entity.RetailPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RetailPriceRepositoryJpa extends JpaRepository<RetailPriceEntity,Long> {
    @Query(value = "SELECT * FROM RETAILPRICE  rp  where ?1 BETWEEN rp.Start_DATE AND rp.END_DATE " +
            "AND rp.PRODUCT_ID = ?2 AND rp.BRAND_ID = ?3", nativeQuery = true)
    List<RetailPriceEntity> findRetailPriceByateDateAndProductIdAndBrandId(LocalDateTime applyDate, Integer productId, Long BrandId);
}
