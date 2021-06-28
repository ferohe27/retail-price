package com.vass.retail_price.pvp.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PriceDetailDto implements Serializable {
    private Long brandId;
    private LocalDateTime applyDate;
    private Integer productId;

}
