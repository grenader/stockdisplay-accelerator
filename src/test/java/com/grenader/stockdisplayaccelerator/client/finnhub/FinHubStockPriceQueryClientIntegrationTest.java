package com.grenader.stockdisplayaccelerator.client.finnhub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class FinHubStockPriceQueryClientIntegrationTest {

    @Autowired
    private FinHubStockPriceQueryClient client;

    @Test
    @DisplayName("trying to fetch a price")
    void fetchPrice() {
        StepVerifier.create(client.fetchPrice("MSFT")
                .doOnNext(System.out::println))
                .assertNext(  p -> assertThat(p).isGreaterThan(0))
                .verifyComplete();
    }

    @Test
    @DisplayName("trying to fetch a price for nonexistent symbol, should get an empty stream")
    void fetchPrice_wrongSymbol() {
        StepVerifier.create(client.fetchPrice("XXX")
                .doOnNext(System.out::println))
                .verifyComplete();
    }
}