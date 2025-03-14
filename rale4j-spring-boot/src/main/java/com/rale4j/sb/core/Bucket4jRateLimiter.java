package com.rale4j.sb.core;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.local.LocalBucketBuilder;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Rate limiter implementation using Bucket4j.
 */
@Component("bucket4j")
public class Bucket4jRateLimiter implements RateLimitStrategy {
    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

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
        Bucket bucket = buckets.computeIfAbsent(key, k -> {
            Refill refill = Refill.intervally(limit, Duration.ofSeconds(duration));
            Bandwidth bandwidth = Bandwidth.classic(limit, refill);
            return new LocalBucketBuilder().addLimit(bandwidth).build();
        });
        return bucket.tryConsume(1);
    }
}