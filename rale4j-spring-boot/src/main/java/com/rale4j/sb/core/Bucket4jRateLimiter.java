package com.rale4j.sb.core;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Rate limiter implementation using Bucket4j.
 */
@Component("bucket4j")
public class Bucket4jRateLimiter implements RateLimitStrategy {
    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(String key, int limit, int duration) {
        Bucket bucket = buckets.computeIfAbsent(key, k -> {
            Refill refill = Refill.intervally(limit, Duration.ofSeconds(duration));
            Bandwidth bandwidth = Bandwidth.classic(limit, refill);
            return Bucket.builder().addLimit(bandwidth).build();
        });
        return bucket.tryConsume(1);
    }
}