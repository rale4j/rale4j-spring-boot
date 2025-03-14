package com.rale4j.sb.unit;

import com.rale4j.sb.core.RedisRateLimiter;
import com.rale4j.sb.core.RateLimitStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RedisRateLimiter.
 */
class RedisRateLimiterTest {

    /**
     * Tests that the allowRequest method of the RedisRateLimiter returns true for a valid request.
     */
    @Test
    void testAllowRequest() {
        RedisTemplate<String, String> redisTemplate = mock(RedisTemplate.class);
        ValueOperations<String, String> valueOperations = mock(ValueOperations.class);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.increment(anyString(), anyLong())).thenReturn(1L);

        RateLimitStrategy strategy = new RedisRateLimiter(redisTemplate);
        assertTrue(strategy.allowRequest("key1", 10, 60));
    }
}