package com.rale4j.sb.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Factory class for creating rate limit strategies.
 */
@Component
public class RateLimitFactory {
    private static final Logger logger = Logger.getLogger(RateLimitFactory.class.getName());
    private final Map<String, RateLimitStrategy> strategies;

    /**
     * Constructor for RateLimitFactory.
     *
     * @param strategies a map of rate limit strategies
     */
    @Autowired
    public RateLimitFactory(Map<String, RateLimitStrategy> strategies) {
        this.strategies = strategies;
    }

    /**
     * Retrieves a rate limit strategy by name.
     *
     * @param name the name of the rate limit strategy
     * @return the rate limit strategy, or a safe fallback strategy
     */
    public RateLimitStrategy getStrategy(String name) {
        return Optional.ofNullable(strategies.get(name))
                .orElseGet(() -> {
                    logger.warning("Unknown rate limit strategy: " + name + ". Falling back to 'guava'.");
                    return strategies.get("guava"); // Ensure "guava" exists in strategies
                });
    }
}
