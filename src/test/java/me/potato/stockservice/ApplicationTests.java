package me.potato.stockservice;

import io.rsocket.transport.netty.client.TcpClientTransport;
import me.potato.stockservice.dto.StockTickDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.test.StepVerifier;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private RSocketRequester.Builder builder;

    @Test
    void stockTickTest() {
        var requester = builder.transport(TcpClientTransport.create("localhost", 7070));
        var flux = requester.route("stock.ticks")
                .retrieveFlux(StockTickDto.class)
                .doOnNext(System.out::println)
                .take(10);

        StepVerifier.create(flux)
                .expectNextCount(10)
                .verifyComplete();

    }
}
