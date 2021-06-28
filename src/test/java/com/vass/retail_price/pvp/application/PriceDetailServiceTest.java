package com.vass.retail_price.pvp.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
class PriceDetailServiceTest {
    @LocalServerPort
    private Integer port;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private static TestRestTemplate client = null;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @ParameterizedTest
    @MethodSource("parametersUseCases")
    public void mustGetPricesProductByApplyDateProductIdAndBrandId(String testDescription, String expected, PriceDetailDto priceDetailDto) throws Exception {
        //Given
        log.info(testDescription);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(priceDetailDto);
        // When
        ResultActions resultActions = assertRequestWithBody("/api/price/detail/products/brandid/{brandId}/productid/{productId}/applydate/{applydate}",
                priceDetailDto.getBrandId(), priceDetailDto.getApplyDate(),priceDetailDto.getProductId());
        //Then
        resultActions.andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().string(expected));
    }

    static List<Arguments> parametersUseCases() {
       return Arrays.asList(
               Arguments.arguments("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)",
                       "[{\"brandId\":1,\"startDate\":\"2020-06-14T00:00:00\",\"endDate\":\"2020-12-31T23:59:59\",\"productId\":35455,\"priority\":0,\"price\":35.5,\"priceList\":1,\"currency\":\"EUR\"}]",
                       mapToPriceDetailDto(LocalDateTime.of(2020, 6, 14, 10, 01, 01), 35455, 1L)),

               Arguments.arguments("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)",
                       "[{\"brandId\":1,\"startDate\":\"2020-06-14T15:00:00\",\"endDate\":\"2020-06-14T18:30:00\",\"productId\":35455,\"priority\":1,\"price\":25.45,\"priceList\":2,\"currency\":\"EUR\"}]",
                       mapToPriceDetailDto(LocalDateTime.of(2020, 6, 14, 16, 01, 01), 35455, 1L)),

               Arguments.arguments("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)",
                       "[{\"brandId\":1,\"startDate\":\"2020-06-14T00:00:00\",\"endDate\":\"2020-12-31T23:59:59\",\"productId\":35455,\"priority\":0,\"price\":35.5,\"priceList\":1,\"currency\":\"EUR\"}]",
                       mapToPriceDetailDto(LocalDateTime.of(2020, 6, 14, 21, 01, 01), 35455, 1L)),

               Arguments.arguments("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)",
                       "[{\"brandId\":1,\"startDate\":\"2020-06-15T00:00:00\",\"endDate\":\"2020-06-15T11:00:00\",\"productId\":35455,\"priority\":1,\"price\":30.5,\"priceList\":3,\"currency\":\"EUR\"}]",
                       mapToPriceDetailDto(LocalDateTime.of(2020, 6, 15, 10, 01, 01), 35455, 1L)),
               Arguments.arguments("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)",
                       "[{\"brandId\":1,\"startDate\":\"2020-06-15T16:00:00\",\"endDate\":\"2020-12-31T23:59:59\",\"productId\":35455,\"priority\":1,\"price\":38.95,\"priceList\":4,\"currency\":\"EUR\"}]",
                       mapToPriceDetailDto(LocalDateTime.of(2020, 6, 16, 21, 01, 05), 35455, 1L))
       );
    }
    private static PriceDetailDto mapToPriceDetailDto(LocalDateTime applyDate, Integer productId, Long brandId) {
        return PriceDetailDto.builder()
                .applyDate(applyDate)
                .productId(productId)
                .brandId(brandId)
                .build();
    }

    protected ResultActions assertRequestWithBody(String url,Long brandId, LocalDateTime applyDate, Integer productId) throws Exception {
        return mockMvc.perform(get(url, brandId,productId,applyDate)
                .contentType(MediaType.APPLICATION_JSON));
    }
}