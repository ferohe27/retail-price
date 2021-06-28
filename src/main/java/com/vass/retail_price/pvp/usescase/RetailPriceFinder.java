package com.vass.retail_price.pvp.usescase;

import com.vass.retail_price.pvp.domain.RetailPrice;
import com.vass.retail_price.pvp.domain.StartDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RetailPriceFinder {
    List<RetailPrice> getPriceRetailByParameter(LocalDateTime startDate, Integer productId, Long brandId);
}
