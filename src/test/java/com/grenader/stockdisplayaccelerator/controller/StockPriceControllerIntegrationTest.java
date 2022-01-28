package com.grenader.stockdisplayaccelerator.controller;

import com.grenader.stockdisplayaccelerator.model.StockInfo;
import com.grenader.stockdisplayaccelerator.service.StockPriceService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("integration")
@SpringBootTest
class StockPriceControllerIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void getPrice() {
        WebTestClient webTestClient = WebTestClient.bindToApplicationContext(applicationContext).build();

        String responseBody = webTestClient.get().uri("/stock/MSFT/price")
                .exchange().expectBody(String.class)
                .returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(Double.valueOf(responseBody)).isGreaterThan(0d);

        System.out.println("responseBody = " + responseBody);

    }
}
