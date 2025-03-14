package com.rale4j.sb.core;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Rate limiter implementation using the Token Bucket algorithm.
 */
@Component("tokenBucket")
public class TokenBucketRateLimiter implements RateLimitStrategy {
    private final ConcurrentHashMap<String, AtomicLong> tokensMap = new ConcurrentHashMap<>();

    /**
     * Allows or denies a request based on the rate limit.
     *
     * @param key the key to identify the rate limit bucket
     * @param limit the maximum number of tokens available
     * @param duration the duration in seconds for the rate limit
     * @return true if the request is allowed, false otherwise
     */
    @Override
    public boolean allowRequest(String key, int limit, int duration) {
        tokensMap.putIfAbsent(key, new AtomicLong(limit));
        AtomicLong tokens = tokensMap.get(key);

        while (true) {
            long currentTokens = tokens.get();
            if (currentTokens <= 0) return false;
            if (tokens.compareAndSet(currentTokens, currentTokens - 1)) return true;
        }
    }
}