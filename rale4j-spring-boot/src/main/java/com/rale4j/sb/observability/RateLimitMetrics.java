package com.rale4j.sb.observability;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

/**
 * Tracks rate-limiting metrics using Micrometer.
 */
@Component
public class RateLimitMetrics {
    private final Counter allowedRequestsCounter;
    private final Counter blockedRequestsCounter;

    /**
     * Constructs a new RateLimitMetrics instance.
     *
     * @param registry the Micrometer registry
     */
    public RateLimitMetrics(MeterRegistry registry) {
        this.allowedRequestsCounter = Counter.builder("rale4j.requests.allowed")
                .description("Total allowed requests")
                .register(registry);

        this.blockedRequestsCounter = Counter.builder("rale4j.requests.blocked")
                .description("Total blocked requests")
                .register(registry);
    }

    /**
     * Increments the allowed requests counter.
     */
    public void incrementAllowedRequests() {
        allowedRequestsCounter.increment();
    }

    /**
     * Increments the blocked requests counter.
     */
    public void incrementBlockedRequests() {
        blockedRequestsCounter.increment();
    }
}