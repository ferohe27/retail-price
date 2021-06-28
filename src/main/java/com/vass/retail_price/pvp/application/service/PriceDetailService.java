package com.vass.retail_price.pvp.application.service;

import com.vass.retail_price.pvp.application.PriceDetailDto;
import com.vass.retail_price.pvp.application.PriceDetailResponse;
import com.vass.retail_price.pvp.usescase.RetailPriceFinder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/price/detail")
public class PriceDetailService {
    private final RetailPriceFinder retailPriceFinder;

    public PriceDetailService(RetailPriceFinder retailPriceFinder) {
        this.retailPriceFinder = retailPriceFinder;
    }

    @Operation(summary = "find product price for apply date, product id and brand id")
    @ApiResponses(value = {
            @ApiResponse(
            responseCode = "200", description = "Found product price ",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PriceDetailResponse.class))) }
    ),@ApiResponse(responseCode = "404", description = "Product price not found",
            content = @Content) })
    @GetMapping("/products/brandid/{brandId}/productid/{productId}/applydate/{applyDate}")
    public List<PriceDetailResponse> getProductsPrice(
            @PathVariable Long brandId,
            @PathVariable Integer productId,
            @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime applyDate){
     return this.retailPriceFinder.getPriceRetailByParameter(
             applyDate,productId,brandId)
             .stream()
             .map(PriceDetailResponse::mapToPriceDetailResponse)
             .collect(Collectors.toList());
    }
}
