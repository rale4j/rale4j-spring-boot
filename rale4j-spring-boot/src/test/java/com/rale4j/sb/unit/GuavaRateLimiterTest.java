package com.rale4j.sb.unit;

import com.rale4j.sb.core.GuavaRateLimiter;
import com.rale4j.sb.core.RateLimitStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the GuavaRateLimiter.
 */
class GuavaRateLimiterTest {
    /**
     * Tests that the allowRequest method of the GuavaRateLimiter returns true for a valid request.
     */
    @Test
    void testAllowRequest() {
        RateLimitStrategy strategy = new GuavaRateLimiter();
        assertTrue(strategy.allowRequest("key1", 10, 60));
    }
}