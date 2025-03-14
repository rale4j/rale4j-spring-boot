package com.rale4j.sb.security;

import com.rale4j.sb.core.RateLimitStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the JwtRateLimiter.
 */
class JwtRateLimiterTest {
    /**
     * Tests that the allowRequest method of the JwtRateLimiter returns true for a valid request.
     */
    @Test
    void testAllowRequest() {
        RateLimitStrategy strategy = new JwtRateLimiter();
        assertTrue(strategy.allowRequest("key1", 10, 60));
    }
}