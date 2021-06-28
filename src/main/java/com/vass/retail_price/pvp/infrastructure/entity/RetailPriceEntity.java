package com.vass.retail_price.pvp.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RetailPrice")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailPriceEntity {
    @Id
    @Column(name = "RETAIL_PRICE_ID")
    private Long retailPriceId;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "brandId")
    private BrandEntity brandId;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "PRICE_LIST")
    private Integer priceList;

    @Column(name = "CURR")
    private String currency;
}
