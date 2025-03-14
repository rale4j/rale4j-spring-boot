package com.rale4j.sb.security;

import com.rale4j.sb.core.RateLimitStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the OAuth2RateLimiter.
 */
class OAuth2RateLimiterTest {
    /**
     * Tests that the allowRequest method of the OAuth2RateLimiter returns true for a valid request.
     */
    @Test
    void testAllowRequest() {
        RateLimitStrategy strategy = new OAuth2RateLimiter();
        assertTrue(strategy.allowRequest("key1", 10, 60));
    }
}