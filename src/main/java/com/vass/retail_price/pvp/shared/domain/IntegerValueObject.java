package com.vass.retail_price.pvp.shared.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class IntegerValueObject {
    private Integer value;

    public IntegerValueObject(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

}
