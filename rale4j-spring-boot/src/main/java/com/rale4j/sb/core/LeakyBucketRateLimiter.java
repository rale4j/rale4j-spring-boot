package com.rale4j.sb.core;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Rate limiter implementation using the Leaky Bucket algorithm.
 */
@Component("leakyBucket")
public class LeakyBucketRateLimiter implements RateLimitStrategy {
    private final ConcurrentHashMap<String, AtomicLong> bucketMap = new ConcurrentHashMap<>();

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
        bucketMap.putIfAbsent(key, new AtomicLong(0));
        AtomicLong bucket = bucketMap.get(key);

        long current = bucket.get();
        if (current >= limit) return false;
        return bucket.compareAndSet(current, current + 1);
    }
}