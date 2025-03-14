package com.rale4j.sb.unit;

import com.rale4j.sb.annotation.Rale4j;
import com.rale4j.sb.core.RateLimitFactory;
import com.rale4j.sb.core.RateLimitStrategy;
import com.rale4j.sb.enforcement.RateLimitAspect;
import com.rale4j.sb.enforcement.RateLimitKeyGenerator;
import com.rale4j.sb.exception.RateLimitExceededException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RateLimitAspect.
 */
class RateLimitAspectTest {

    /**
     * Tests that the enforceRateLimit method of the RateLimitAspect does not throw an exception
     * when the rate limit is not exceeded.
     *
     * @throws Throwable if the underlying method throws an exception
     */
    @Test
    void testEnforceRateLimit() throws Throwable {
        RateLimitStrategy strategy = mock(RateLimitStrategy.class);
        when(strategy.allowRequest(anyString(), anyInt(), anyInt())).thenReturn(true);

        RateLimitFactory rateLimitFactory = mock(RateLimitFactory.class);
        when(rateLimitFactory.getStrategy(anyString())).thenReturn(strategy);

        RateLimitKeyGenerator keyGenerator = mock(RateLimitKeyGenerator.class);
        when(keyGenerator.generateKey(any())).thenReturn("key1");

        RateLimitAspect aspect = new RateLimitAspect();
        aspect.rateLimitFactory = rateLimitFactory;
        aspect.keyGenerator = keyGenerator;

        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        Rale4j rale4j = mock(Rale4j.class);
        when(rale4j.strategy()).thenReturn("guava");
        when(rale4j.limit()).thenReturn(10);
        when(rale4j.duration()).thenReturn(60);

        assertDoesNotThrow(() -> aspect.enforceRateLimit(joinPoint, rale4j));
    }
}