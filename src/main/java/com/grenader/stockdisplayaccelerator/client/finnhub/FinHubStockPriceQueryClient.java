package com.grenader.stockdisplayaccelerator.client.finnhub;

import com.grenader.stockdisplayaccelerator.client.finnhub.dto.FinHubStockPrice;
import com.grenader.stockdisplayaccelerator.service.StockPriceQueryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FinHubStockPriceQueryClient implements StockPriceQueryClient {

    @Value("${stockdisplay.client.finHub.baseUrl:https://finnhub.io/api/}")
    private String finHubBaseUrl;

    @Value("${stockdisplay.client.finHub.token}")
    private String finHubApiToken;

    private final WebClient.Builder builder;

    @Override
    public Mono<Double> fetchPrice(String ticker) {
        WebClient webClient = builder.baseUrl(finHubBaseUrl).build();

        Mono<Double> currentPrice = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("v1/quote")
                        .queryParam("symbol", ticker)
                        .queryParam("token", finHubApiToken)
                        .build())
                .retrieve()
                .bodyToMono(FinHubStockPrice.class)
                .map(FinHubStockPrice::getCurrentPrice)
                .filter(price -> price > 0); // skipping zero prices.

        return currentPrice;
    }
}
