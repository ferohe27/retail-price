package com.vass.retail_price.pvp.domain;

import com.vass.retail_price.pvp.shared.domain.IntegerValueObject;

public final class ProductId extends IntegerValueObject {
    public ProductId(Integer value) {
        super(value);
        this.validatedValueNotNull(value);
    }
    private void validatedValueNotNull(Integer value){
        if(value == null){
            throw new RetailPriceBusinessException("Product Id no puede ser null, por favor ingrese un valor");
        }
    }
}
