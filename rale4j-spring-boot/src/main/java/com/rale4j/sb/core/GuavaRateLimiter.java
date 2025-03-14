package com.rale4j.sb.core;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

/**
 * Rate limiter implementation using Guava.
 */
@Component("guava")
public class GuavaRateLimiter implements RateLimitStrategy {

    /**
     * Allows or denies a request based on the rate limit.
     *
     * @param key the key to identify the rate limit bucket
     * @param limit the maximum number of requests allowed
     * @param duration the duration in seconds for the rate limit
     * @return true if the request is allowed, false otherwise
     */
    @Override
    public boolean allowRequest(String key, int limit, int duration) {
        RateLimiter rateLimiter = RateLimiter.create(limit / (double) duration);
        return rateLimiter.tryAcquire();
    }
}
