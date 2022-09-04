package me.potato.stockservice.controller;

import lombok.RequiredArgsConstructor;
import me.potato.stockservice.dto.StockTickDto;
import me.potato.stockservice.service.StockService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Controller
public class StockController {
    private final StockService stockService;

    @MessageMapping("stock.ticks")
    public Flux<StockTickDto> getStockPrice() {
        return stockService.getStockPrice();
    }
}
