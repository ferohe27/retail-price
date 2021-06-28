package com.vass.retail_price.pvp.domain;

import java.util.List;

public interface RetailPriceRepository {
    List<RetailPrice> findPricePructoByDateAndIdentifierAndBrandId(StartDate startDate,ProductId prouctId, BrandId brandId);
}
