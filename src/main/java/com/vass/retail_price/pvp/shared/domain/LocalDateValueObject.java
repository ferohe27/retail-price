package com.vass.retail_price.pvp.shared.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@EqualsAndHashCode
public abstract class LocalDateValueObject {
    protected LocalDateTime value;

    public LocalDateValueObject(LocalDateTime value) {
        this.value = value;
    }

    public LocalDateTime value() {
        return value;
    }


}
