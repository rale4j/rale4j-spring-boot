package com.rale4j.sb.core;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * Rate limiter implementation using Redis.
 */
@Component("redis")
public class RedisRateLimiter implements RateLimitStrategy {
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * Constructor for RedisRateLimiter.
     *
     * @param redisTemplate the Redis template to be used for rate limiting
     */
    public RedisRateLimiter(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

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
        Long count = redisTemplate.opsForValue().increment(key, 1);
        if (count != null && count == 1) {
            redisTemplate.expire(key, duration, TimeUnit.SECONDS);
        }
        return count != null && count <= limit;
    }
}