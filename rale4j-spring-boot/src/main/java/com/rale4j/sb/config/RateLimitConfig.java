package com.rale4j.sb.config;

import com.rale4j.sb.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Configuration class for setting up rate limiting strategies.
 */
@Configuration
public class RateLimitConfig {

    /**
     * Creates a Guava-based rate limiter bean.
     *
     * @return a new instance of GuavaRateLimiter
     */
    @Bean
    public RateLimitStrategy guavaRateLimiter() {
        return new GuavaRateLimiter();
    }

    /**
     * Creates a Redis-based rate limiter bean.
     *
     * @param redisTemplate the Redis template to be used by the rate limiter
     * @return a new instance of RedisRateLimiter
     */
    @Bean
    public RateLimitStrategy redisRateLimiter(RedisTemplate<String, String> redisTemplate) {
        return new RedisRateLimiter(redisTemplate);
    }

    /**
     * Creates a token bucket-based rate limiter bean.
     *
     * @return a new instance of TokenBucketRateLimiter
     */
    @Bean
    public RateLimitStrategy tokenBucketRateLimiter() {
        return new TokenBucketRateLimiter();
    }

    /**
     * Creates a Resilience4j-based rate limiter bean.
     *
     * @return a new instance of Resilience4jRateLimiter
     */
    @Bean
    public RateLimitStrategy resilience4jRateLimiter() {
        return new Resilience4jRateLimiter();
    }

    /**
     * Creates a Bucket4j-based rate limiter bean.
     *
     * @return a new instance of Bucket4jRateLimiter
     */
    @Bean
    public RateLimitStrategy bucket4jRateLimiter() {
        return new Bucket4jRateLimiter();
    }
}