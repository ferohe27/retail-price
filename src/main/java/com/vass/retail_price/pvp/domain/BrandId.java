package com.vass.retail_price.pvp.domain;

import com.vass.retail_price.pvp.shared.domain.LongValueObject;

public final class BrandId extends LongValueObject {
    public BrandId(final Long value) {
        super(value);
        this.validatedValueNotNull(value);
    }

    private void validatedValueNotNull(Long value){
        if(value == null){
            throw new RetailPriceBusinessException("Brand Id no puede ser null, por favor ingrese un valor");
        }
    }
}
