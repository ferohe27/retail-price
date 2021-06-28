package com.vass.retail_price.pvp.usescase;

import com.vass.retail_price.pvp.domain.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class RetailPriceFinderImpl implements RetailPriceFinder{
    private final RetailPriceRepository retailPriceRepository;

    public RetailPriceFinderImpl(RetailPriceRepository retailPriceRepository) {
        this.retailPriceRepository = retailPriceRepository;
    }

    @Override
    public List<RetailPrice> getPriceRetailByParameter(
            LocalDateTime startDate,
            Integer productId,
            Long brandId) {

        List<RetailPrice> pricePructoByDateAndIdentifierAndBrandId = this.retailPriceRepository.findPricePructoByDateAndIdentifierAndBrandId(
                new StartDate(startDate),
                new ProductId(productId),
                new BrandId(brandId)
        );
        //Aplica el desambiguador de apliacion de precios.
        // Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
        List<RetailPrice> retailPrices = applyPriority(pricePructoByDateAndIdentifierAndBrandId);
        return retailPrices;
    }

    private List<RetailPrice> applyPriority(List<RetailPrice> pricePructoByDateAndIdentifierAndBrandId) {
        if(pricePructoByDateAndIdentifierAndBrandId.isEmpty()){
            return pricePructoByDateAndIdentifierAndBrandId;
        }
        Comparator<RetailPrice> comparator = (p1, p2) -> p2.getPriority().value().compareTo(p1.getPriority().value()) ;

        pricePructoByDateAndIdentifierAndBrandId.sort(comparator);
        List<RetailPrice> retailPrices = Collections.singletonList(pricePructoByDateAndIdentifierAndBrandId.stream().findFirst()
                .orElse(RetailPrice.builder().build()));
        return retailPrices;
    }
}
