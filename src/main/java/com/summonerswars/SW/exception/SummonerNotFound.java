package com.summonerswars.SW.exception;


public class SummonerNotFound extends RuntimeException {
    public SummonerNotFound() {
        super("Product not found.");
    }

    public SummonerNotFound(String message) {
        super(message);
    }
}
