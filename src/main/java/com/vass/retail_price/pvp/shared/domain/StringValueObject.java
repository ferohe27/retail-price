package com.vass.retail_price.pvp.shared.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class StringValueObject {
    private String value;

    public StringValueObject(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return this.value();
    }
}
