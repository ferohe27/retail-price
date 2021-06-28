package com.vass.retail_price.pvp.infrastructure.repository;

import com.vass.retail_price.pvp.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RetailPriceRepositoryImpl implements RetailPriceRepository {
    private final RetailPriceRepositoryJpa retailPriceRepositoryJpa;

    public RetailPriceRepositoryImpl(RetailPriceRepositoryJpa retailPriceRepositoryJpa) {
        this.retailPriceRepositoryJpa = retailPriceRepositoryJpa;
    }

    @Override
    public List<RetailPrice> findPricePructoByDateAndIdentifierAndBrandId(StartDate startDate, ProductId productId, BrandId brandId) {
        return this.retailPriceRepositoryJpa
                .findRetailPriceByateDateAndProductIdAndBrandId(startDate.value(), productId.value(), brandId.value())
                .stream()
                .map(this::mapToRetailPrice)
                .collect(Collectors.toList());
    }

    private RetailPrice mapToRetailPrice(com.vass.retail_price.pvp.infrastructure.entity.RetailPriceEntity item) {
        return new RetailPrice(
                new BrandId(item.getBrandId().getBrandId()),
                new StartDate(item.getStartDate()),
                new EndDate(item.getEndDate()),
                new PriceList(item.getPriceList()),
                new ProductId(item.getProductId()),
                new Priority(item.getPriority()),
                new Price(item.getPrice()),
                new Currency(item.getCurrency())
        );
    }
}
