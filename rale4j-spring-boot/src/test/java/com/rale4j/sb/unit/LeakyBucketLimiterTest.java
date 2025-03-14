package com.rale4j.sb.unit;

import com.rale4j.sb.core.LeakyBucketRateLimiter;
import com.rale4j.sb.core.RateLimitStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LeakyBucketRateLimiter.
 */
class LeakyBucketLimiterTest {

    /**
     * Tests that the allowRequest method of the LeakyBucketRateLimiter returns true for a valid request.
     */
    @Test
    void testAllowRequest() {
        RateLimitStrategy strategy = new LeakyBucketRateLimiter();
        assertTrue(strategy.allowRequest("key1", 10, 60));
    }

    /**
     * Tests that the allowRequest method of the LeakyBucketRateLimiter returns false when the limit is exceeded.
     */
    @Test
    void testDenyRequestWhenLimitExceeded() {
        RateLimitStrategy strategy = new LeakyBucketRateLimiter();
        for (int i = 0; i < 10; i++) {
            assertTrue(strategy.allowRequest("key2", 10, 60));
        }
        assertFalse(strategy.allowRequest("key2", 10, 60));
    }

    /**
     * Tests that the allowRequest method of the LeakyBucketRateLimiter returns true for different keys.
     */
    @Test
    void testAllowRequestWithDifferentKeys() {
        RateLimitStrategy strategy = new LeakyBucketRateLimiter();
        assertTrue(strategy.allowRequest("key3", 10, 60));
        assertTrue(strategy.allowRequest("key4", 10, 60));
    }
}