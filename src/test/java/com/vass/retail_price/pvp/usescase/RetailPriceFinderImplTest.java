package com.vass.retail_price.pvp.usescase;

import com.vass.retail_price.pvp.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetailPriceFinderImplTest {
    @InjectMocks
    private  RetailPriceFinderImpl retailPriceFinder;
    @Mock
    private RetailPriceRepository retailPriceRepository;

    @Test
    public void findPriceProductGivenAnApplydateAndProductIdAndBrandId(){
        //Given
        LocalDateTime valuApplyDate = LocalDateTime.of(2014, 06, 14, 9, 0, 0);
        StartDate startDate = new StartDate(valuApplyDate);
        int valueProductId = 35455;
        ProductId productId = new ProductId(valueProductId);
        long valueBrand = 1L;
        BrandId brandId = new BrandId(valueBrand);
        List<RetailPrice> prices = Collections.singletonList(new RetailPrice(
                brandId,
                startDate,
                new EndDate(LocalDateTime.of(2014, 12, 14, 19, 0, 0)),
                new PriceList(1),
                new ProductId(valueProductId),
                new Priority(0),
                new Price(20.45),
                new Currency("EUR")
        ));
        when(retailPriceRepository.findPricePructoByDateAndIdentifierAndBrandId(startDate,productId,brandId)).thenReturn(prices);
        List<RetailPrice> priceRetailByParameter = retailPriceFinder.getPriceRetailByParameter(valuApplyDate, valueProductId, valueBrand);

        assertFalse(priceRetailByParameter.isEmpty());
        priceRetailByParameter.forEach( item  ->
                        assertEquals(20.45, item.getPrice().value())
                );
        verify(retailPriceRepository,times(1)).findPricePructoByDateAndIdentifierAndBrandId(startDate,productId,brandId);
    }
    @Test
    @DisplayName("When to an date exist above two prices then apply priority")
    public void givenAnDateApplyPriorityWhenExistTwoPrice(){
        //Given
        LocalDateTime valuApplyDate = LocalDateTime.of(2014, 06, 14, 9, 0, 0);
        StartDate startDate = new StartDate(valuApplyDate);
        int valueProductId = 35455;
        ProductId productId = new ProductId(valueProductId);
        long valueBrand = 1L;
        BrandId brandId = new BrandId(valueBrand);
        List<RetailPrice> prices = Arrays.asList(new RetailPrice(
                        brandId,
                        new StartDate(LocalDateTime.of(2020,6,14,0,0,0)),
                        new EndDate(LocalDateTime.of(2020,12,31,23,59,59)),
                        new PriceList(1),
                        new ProductId(valueProductId),
                        new Priority(0),
                        new Price(20.45),
                        new Currency("EUR")
                ),
                new RetailPrice(
                        brandId,
                        new StartDate(LocalDateTime.of(2020,6,14,0,0,0)),
                        new EndDate(LocalDateTime.of(2020,6,14,18,30,0)),
                        new PriceList(1),
                        new ProductId(valueProductId),
                        new Priority(1),
                        new Price(40.45),
                        new Currency("EUR")
                ));
        when(retailPriceRepository.findPricePructoByDateAndIdentifierAndBrandId(startDate,productId,brandId)).thenReturn(prices);
        List<RetailPrice> priceRetailByParameter = retailPriceFinder.getPriceRetailByParameter(valuApplyDate, valueProductId, valueBrand);

        assertFalse(priceRetailByParameter.isEmpty());
        priceRetailByParameter.forEach( item  -> {
                    assertEquals(1, item.getPriority().value());
                    assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), item.getStartDate().value());
                    assertEquals(LocalDateTime.of(2020,6,14,18,30,0), item.getEndDate().value());
                    assertEquals(40.45,item.getPrice().value());
                }
        );
        verify(retailPriceRepository,times(1)).findPricePructoByDateAndIdentifierAndBrandId(startDate,productId,brandId);
    }
    @Test
    @DisplayName("Validate when It´s have been searching product price  and brand id is null")
    public void whenItHaveBeenSearchingPriceProductAndBrandIdIsNull(){
        LocalDateTime valuApplyDate = LocalDateTime.of(2014, 06, 14, 9, 0, 0);
        RetailPriceBusinessException retailPriceBusinessException = assertThrows(RetailPriceBusinessException.class, () ->
                retailPriceFinder.getPriceRetailByParameter(valuApplyDate, 1, null)
        );

      assertEquals(retailPriceBusinessException.getMessage(),"Brand Id no puede ser null, por favor ingrese un valor");
    }

    @Test
    @DisplayName("Validate when It´s have been searching product price  and product id is null")
    public void whenItHaveBeenSearchingPriceProductAndProductIdIsNull(){
        LocalDateTime valuApplyDate = LocalDateTime.of(2014, 6, 14, 9, 0, 0);
        RetailPriceBusinessException retailPriceBusinessException = assertThrows(RetailPriceBusinessException.class, () ->
                retailPriceFinder.getPriceRetailByParameter(valuApplyDate, null, 1L)
        );

        assertEquals(retailPriceBusinessException.getMessage(),"Product Id no puede ser null, por favor ingrese un valor");
    }

    @Test
    @DisplayName("Validate when It´s have been searching product price  and ApplyDate is null")
    public void whenItHaveBeenSearchingPriceProductAndApplyDateIsNull(){
        RetailPriceBusinessException retailPriceBusinessException = assertThrows(RetailPriceBusinessException.class, () ->
                retailPriceFinder.getPriceRetailByParameter(null, 33456, 1L)
        );

        assertEquals(retailPriceBusinessException.getMessage(),"La fecha no puede ser null, por favor ingrese un valor");
    }
}