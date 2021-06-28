package com.vass.retail_price.pvp.domain;

import com.vass.retail_price.pvp.shared.domain.LocalDateValueObject;

import java.time.LocalDateTime;

public final class StartDate extends LocalDateValueObject {
    public StartDate(LocalDateTime value) {
        super(value);
        this.validatedValueNotNull(value);
    }

    private void validatedValueNotNull(LocalDateTime value){
        if(value == null){
            throw new RetailPriceBusinessException("La fecha no puede ser null, por favor ingrese un valor");
        }
    }
}
