package com.vass.retail_price.pvp.shared.domain;

import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode
public abstract class DoubleValueObject {
    protected Double value;

    public DoubleValueObject(Double value) {
        this.value = value;
    }

    public Double value() {
        return value;
    }
}
