package com.vass.retail_price.pvp.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public final class RetailPrice {
    private final BrandId brandId;
    private final StartDate startDate;
    private final EndDate endDate;
    private final PriceList priceList;
    private final ProductId prouctId;
    private final Priority priority;
    private final Price price;
    private final Currency currency;

    public RetailPrice(
            BrandId brandId,
            StartDate startDate,
            EndDate endDate,
            PriceList priceList,
            ProductId prouctId,
            Priority priority,
            Price price,
            Currency currency
    ) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.prouctId = prouctId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }
}
