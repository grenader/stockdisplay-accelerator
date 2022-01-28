package com.grenader.stockdisplayaccelerator.service;

import reactor.core.publisher.Mono;

public interface StockPriceQueryClient {

    /**
     * Return a current value of a stock based on its symbol
     * @param ticker stock symbol
     * @return price if existing symbol was given, and empty stream otherwise
     */
    Mono<Double> fetchPrice(String ticker);
}
