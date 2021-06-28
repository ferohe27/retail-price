package com.vass.retail_price.pvp.domain;

import com.vass.retail_price.pvp.shared.domain.LocalDateValueObject;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class EndDate extends LocalDateValueObject {
    public EndDate(LocalDateTime value) {
        super(value);
    }
}
