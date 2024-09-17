package io.micronaut.graal.graalpy;

import io.micronaut.context.annotation.Bean;
import jakarta.inject.Inject;

@Bean
public class DocExample {
    @Inject DealerService dealerService;

    public Object[] play() {
        Object[] cards = new Object[] { 1, 2, 3 };
        cards = dealerService.shuffle(cards);
        // ...
        return cards;
    }
}
