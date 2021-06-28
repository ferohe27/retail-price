package com.vass.retail_price.pvp.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "BRANDS")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandEntity {
    @Id
    @Column(name = "BRAND_ID")
    private Long brandId;

    private String description;
}
