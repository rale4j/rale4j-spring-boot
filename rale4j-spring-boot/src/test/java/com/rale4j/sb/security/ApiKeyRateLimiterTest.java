package com.rale4j.sb.security;


import com.rale4j.sb.core.RateLimitStrategy;
import com.rale4j.sb.security.ApiKeyRateLimiter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ApiKeyRateLimiter.
 */
class ApiKeyRateLimiterTest {
    /**
     * Tests that the allowRequest method of the ApiKeyRateLimiter returns true for a valid request.
     */
    @Test
    void testAllowRequest() {
        RateLimitStrategy strategy = new ApiKeyRateLimiter();
        assertTrue(strategy.allowRequest("key1", 10, 60));
    }
}
