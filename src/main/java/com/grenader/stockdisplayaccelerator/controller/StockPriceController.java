package com.grenader.stockdisplayaccelerator.controller;

import com.grenader.stockdisplayaccelerator.service.StockPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class StockPriceController {

    private final StockPriceService priceService;

    @GetMapping("/stock/{ticker}/price")
    public Mono<String> getPrice(@PathVariable String ticker)
    {
        return priceService.getStockPrice(ticker).map( info -> info.getPrice().toString());
    }
}
