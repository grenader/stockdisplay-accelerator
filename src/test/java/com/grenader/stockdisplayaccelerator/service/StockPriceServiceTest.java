package com.grenader.stockdisplayaccelerator.service;

import com.grenader.stockdisplayaccelerator.model.StockInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class StockPriceServiceTest {

    @Mock
    StockPriceQueryClient client;

    @InjectMocks
    StockPriceService service;

    @Test
    void testLoadStockPrice() {
        Mockito.when(client.fetchPrice(anyString())).thenReturn(Mono.just(10d));

        var ticker = "MSFT";
        StepVerifier.create(service.getStockPrice(ticker))
                .expectNext(new StockInfo(ticker, 10d))
                .verifyComplete();
    }
}