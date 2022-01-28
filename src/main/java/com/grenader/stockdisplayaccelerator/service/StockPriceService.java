package com.grenader.stockdisplayaccelerator.service;

import com.grenader.stockdisplayaccelerator.model.StockInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockPriceService {

    private final StockPriceQueryClient client;

    public Mono<StockInfo> getStockPrice(String ticker) {
        return client.fetchPrice(ticker).map(p -> new StockInfo(ticker, p));
    }
}
