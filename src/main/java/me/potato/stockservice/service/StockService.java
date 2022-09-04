package me.potato.stockservice.service;

import me.potato.stockservice.dto.StockTickDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class StockService {
    private static final Stock AMZN = new Stock("AMZN", 1000, 20);
    private static final Stock AAPL = new Stock("AAPL", 100, 3);
    private static final Stock MSFT = new Stock("MSFT", 200, 5);


    public Flux<StockTickDto> getStockPrice() {
        return Flux.interval(Duration.ofSeconds(2))
                .flatMap(i -> Flux.just(AMZN, AAPL, MSFT))
                .map(stock -> new StockTickDto(stock.getCode(), stock.getPrice()));
    }
}
