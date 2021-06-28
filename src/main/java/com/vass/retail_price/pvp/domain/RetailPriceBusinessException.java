package com.vass.retail_price.pvp.domain;

public class RetailPriceBusinessException extends RuntimeException{
    public RetailPriceBusinessException(String message){
        super(message);
    }

    public RetailPriceBusinessException(String message, Throwable cause){
        super(message,cause);
    }
}
