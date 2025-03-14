package com.rale4j.sb.core;

/**
 * Interface for rate-limiting strategies.
 * <p>
 * All rate-limiting implementations must implement this interface.
 * </p>
 */
public interface RateLimitStrategy {
    /**
     * Checks if a request is allowed based on the rate-limiting rules.
     *
     * @param key      the unique key for the rate limit (e.g., user ID, IP)
     * @param limit    the maximum number of requests allowed
     * @param duration the time window (in seconds) for the rate limit
     * @return true if the request is allowed, false otherwise
     */
    boolean allowRequest(String key, int limit, int duration);
}