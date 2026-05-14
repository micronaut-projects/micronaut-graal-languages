package io.micronaut.graal.graalpy;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record SentimentAnalysis(String fileName, SentimentScores sentiment) {
    @Serdeable
    public record SentimentScores(
            double positive,
            double neutral,
            double negative,
            double compound,
            String label
    ) {
    }
}
