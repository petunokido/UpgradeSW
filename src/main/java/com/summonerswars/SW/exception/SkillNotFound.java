package com.summonerswars.SW.exception;


public class SkillNotFound extends RuntimeException {
    public SkillNotFound() {
        super("Product not found.");
    }

    public SkillNotFound(String message) {
        super(message);
    }
}
