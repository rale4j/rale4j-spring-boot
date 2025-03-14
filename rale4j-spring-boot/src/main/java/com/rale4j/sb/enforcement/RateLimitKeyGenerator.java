package com.rale4j.sb.enforcement;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * Generates unique keys for rate limiting based on the method and its arguments.
 */
@Component
public class RateLimitKeyGenerator {
    /**
     * Generates a unique key for rate limiting.
     *
     * @param joinPoint the join point representing the intercepted method
     * @return a unique key for rate limiting
     */
    public String generateKey(ProceedingJoinPoint joinPoint) {
        // Example: Use method name + user ID (if available) as the key
        String methodName = joinPoint.getSignature().toShortString();
        String userId = extractUserIdFromRequest(joinPoint); // Placeholder for user ID extraction
        return methodName + "-" + userId;
    }

    /**
     * Extracts the user ID from the request (e.g., from JWT, session, or request parameters).
     *
     * @param joinPoint the join point representing the intercepted method
     * @return the user ID, or "anonymous" if not available
     */
    private String extractUserIdFromRequest(ProceedingJoinPoint joinPoint) {
        // Implement logic to extract user ID (e.g., from JWT, session, or request parameters)
        return "anonymous"; // Placeholder
    }
}