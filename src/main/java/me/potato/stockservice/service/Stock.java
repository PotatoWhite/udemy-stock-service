package me.potato.stockservice.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@AllArgsConstructor
public class Stock {
    private final String code;
    private       int    price;
    private final int    volatility;

    private void updatePrice() {
        var random = ThreadLocalRandom.current().nextInt(-1 * volatility, volatility + 1);
        this.price = random + this.price;
        this.price = Math.max(this.price, 0);
    }

    public int getPrice() {
        updatePrice();
        return price;
    }
}
