package com.vass.retail_price.pvp.infrastructure.repository;

import com.vass.retail_price.pvp.domain.BrandId;
import com.vass.retail_price.pvp.domain.ProductId;
import com.vass.retail_price.pvp.domain.RetailPrice;
import com.vass.retail_price.pvp.domain.StartDate;
import com.vass.retail_price.pvp.infrastructure.entity.BrandEntity;
import com.vass.retail_price.pvp.infrastructure.entity.RetailPriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetailPriceRepositoryImplTest {
    @InjectMocks
    private RetailPriceRepositoryImpl retailPriceRepository;
    @Mock
    private RetailPriceRepositoryJpa retailPriceRepositoryJpa;

    @Test
    public void findAllPriceProductGivenAnApplydateAndProductIdAndBrandId() {
        LocalDateTime valueApplyDate = LocalDateTime.of(2021, 06, 14, 10, 0, 0);
        int valueProductId = 35455;
        long valueBrandId = 1L;
        when(retailPriceRepositoryJpa.findRetailPriceByateDateAndProductIdAndBrandId(valueApplyDate,valueProductId,valueBrandId))
                .thenReturn(Arrays.asList(
                        RetailPriceEntity.builder()
                                .brandId(BrandEntity.builder().brandId(1L).description("ZARA").build())
                                .price(40.46)
                                .currency("EUR")
                                .priceList(1)
                                .productId(33455)
                                .retailPriceId(1L)
                                .startDate(LocalDateTime.of(2014,6,12,14,0,0))
                                .endDate(LocalDateTime.of(2014,12,12,13,10,0))
                                .priority(1)
                                .build(),
                        RetailPriceEntity.builder()
                                .brandId(BrandEntity.builder().brandId(1L).description("ZARA").build())
                                .price(33.19)
                                .currency("EUR")
                                .priceList(2)
                                .productId(33455)
                                .retailPriceId(2L)
                                .startDate(LocalDateTime.of(2014,6,12,16,0,0))
                                .endDate(LocalDateTime.of(2014,6,20,13,10,0))
                                .priority(1)
                                .build()

                ));
        List<RetailPrice> pricePructoByDateAndIdentifierAndBrandId = retailPriceRepository.findPricePructoByDateAndIdentifierAndBrandId(
                new StartDate(valueApplyDate),
                new ProductId(valueProductId),
                new BrandId(valueBrandId)

        );

        assertFalse(pricePructoByDateAndIdentifierAndBrandId.isEmpty());
        verify(retailPriceRepositoryJpa,times(1)).findRetailPriceByateDateAndProductIdAndBrandId(valueApplyDate,valueProductId,valueBrandId);
    }

    @Test
    public void givenAnApplydateAndProductIdAndBrandIdThenResultIsEmpty() {
        LocalDateTime valueApplyDate = LocalDateTime.of(2021, 06, 14, 10, 0, 0);
        int valueProductId = 35455;
        long valueBrandId = 12L;
        when(retailPriceRepositoryJpa.findRetailPriceByateDateAndProductIdAndBrandId(valueApplyDate,valueProductId,valueBrandId))
                .thenReturn(Collections.emptyList());
        List<RetailPrice> pricePructoByDateAndIdentifierAndBrandId = retailPriceRepository.findPricePructoByDateAndIdentifierAndBrandId(
                new StartDate(valueApplyDate),
                new ProductId(valueProductId),
                new BrandId(valueBrandId)

        );

        assertTrue(pricePructoByDateAndIdentifierAndBrandId.isEmpty());
        verify(retailPriceRepositoryJpa,times(1)).findRetailPriceByateDateAndProductIdAndBrandId(valueApplyDate,valueProductId,valueBrandId);
    }
}