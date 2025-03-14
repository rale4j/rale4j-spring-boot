package com.rale4j.sb.unit;

import com.rale4j.sb.core.TokenBucketRateLimiter;
import com.rale4j.sb.core.RateLimitStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TokenBucketRateLimiter.
 */
class TokenBucketRateLimiterTest {

    /**
     * Tests that the allowRequest method of the TokenBucketRateLimiter returns true for a valid request.
     */
    @Test
    void testAllowRequest() {
        RateLimitStrategy strategy = new TokenBucketRateLimiter();
        assertTrue(strategy.allowRequest("key1", 10, 60));
    }
}