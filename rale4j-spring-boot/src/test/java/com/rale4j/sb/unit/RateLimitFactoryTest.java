package com.rale4j.sb.unit;

import com.rale4j.sb.core.RateLimitFactory;
import com.rale4j.sb.core.RateLimitStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RateLimitFactoryTest {

    private RateLimitFactory rateLimitFactory;

    @Mock
    private RateLimitStrategy guavaStrategy;

    @Mock
    private RateLimitStrategy leakyBucketStrategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        rateLimitFactory = new RateLimitFactory(Map.of(
                "guava", guavaStrategy,
                "leaky-bucket", leakyBucketStrategy
        ));
    }

    /**
     * Tests that the getStrategy method of the RateLimitFactory returns a valid strategy for a known strategy name.
     */
    @Test
    void testGetStrategy_validStrategies() {
        assertNotNull(rateLimitFactory, "RateLimitFactory should be instantiated");

        RateLimitStrategy strategy = rateLimitFactory.getStrategy("guava");
        assertNotNull(strategy, "Guava strategy should be returned");
        assertEquals(guavaStrategy, strategy, "Guava strategy should match the expected instance");

        strategy = rateLimitFactory.getStrategy("leaky-bucket");
        assertNotNull(strategy, "Leaky bucket strategy should be returned");
        assertEquals(leakyBucketStrategy, strategy, "Leaky bucket strategy should match the expected instance");
    }

    /**
     * Tests that an invalid strategy name falls back to the default (guava).
     */
    @Test
    void testGetStrategy_invalidStrategy() {
        RateLimitStrategy strategy = rateLimitFactory.getStrategy("invalid-strategy");
        assertNotNull(strategy, "Invalid strategy should return the fallback strategy (guava)");
        assertEquals(guavaStrategy, strategy, "Fallback strategy should be 'guava'");
    }

    /**
     * Tests that the factory does not throw an exception when the fallback strategy itself is missing.
     */
    @Test
    void testGetStrategy_noGuavaFallback() {
        rateLimitFactory = new RateLimitFactory(Map.of(
                "leaky-bucket", leakyBucketStrategy
        ));

        RateLimitStrategy strategy = rateLimitFactory.getStrategy("invalid-strategy");
        assertNull(strategy, "Invalid strategy should return null if no valid fallback exists");
    }
}
