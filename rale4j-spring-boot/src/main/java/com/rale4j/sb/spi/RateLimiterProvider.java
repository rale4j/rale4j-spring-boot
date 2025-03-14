package com.rale4j.sb.spi;

import com.rale4j.sb.core.RateLimitStrategy;

/**
 * Service Provider Interface (SPI) for custom rate-limiting strategies.
 * <p>
 * Implement this interface to provide custom rate-limiting implementations.
 * </p>
 */
public interface RateLimiterProvider {
    /**
     * Returns the name of the rate-limiting strategy.
     *
     * @return the strategy name
     */
    String getStrategyName();

    /**
     * Creates a new instance of the rate-limiting strategy.
     *
     * @return the rate-limiting strategy
     */
    RateLimitStrategy createStrategy();
}