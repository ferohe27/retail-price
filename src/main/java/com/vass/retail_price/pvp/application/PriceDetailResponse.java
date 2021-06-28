package com.vass.retail_price.pvp.application;

import com.vass.retail_price.pvp.domain.RetailPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceDetailResponse {
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer productId;
    private Integer priority;
    private Double price;
    private Integer priceList;
    private String currency;

    public static PriceDetailResponse mapToPriceDetailResponse(RetailPrice retailPrice){
        return PriceDetailResponse.builder()
                .brandId(retailPrice.getBrandId().value())
                .startDate(retailPrice.getStartDate().value())
                .endDate(retailPrice.getEndDate().value())
                .productId(retailPrice.getProuctId().value())
                .priority(retailPrice.getPriority().value())
                .price(retailPrice.getPrice().value())
                .priceList(retailPrice.getPriceList().value())
                .currency(retailPrice.getCurrency().value())
                .build();
    }
}
