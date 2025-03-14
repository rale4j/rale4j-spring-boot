package com.rale4j.sb.unit;

import com.rale4j.sb.core.Resilience4jRateLimiter;
import com.rale4j.sb.core.RateLimitStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Resilience4jRateLimiter.
 */
class Resilience4jRateLimiterTest {

    /**
     * Tests that the allowRequest method of the Resilience4jRateLimiter returns true for a valid request.
     */
    @Test
    void testAllowRequest() {
        RateLimitStrategy strategy = new Resilience4jRateLimiter();
        assertTrue(strategy.allowRequest("key1", 10, 60));
    }
}