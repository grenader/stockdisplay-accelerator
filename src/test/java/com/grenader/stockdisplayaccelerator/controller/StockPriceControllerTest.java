package com.grenader.stockdisplayaccelerator.controller;

import com.grenader.stockdisplayaccelerator.model.StockInfo;
import com.grenader.stockdisplayaccelerator.service.StockPriceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebTestClientAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(controllers = StockPriceController.class)
class StockPriceControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    StockPriceService priceService;

    @Test
    void getPrice() {

        Mockito.when(priceService.getStockPrice("MSFT"))
                .thenReturn(Mono.just(new StockInfo("MSFT", 100d)));

        client.get().uri("/stock/MSFT/price")
                .exchange().expectBody(String.class)
                .isEqualTo("100.0");
    }
}
