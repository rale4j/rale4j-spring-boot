package com.rale4j.sb.grpc;

import com.rale4j.sb.core.RateLimitStrategy;
import com.rale4j.sb.core.RateLimitFactory;
import com.rale4j.sb.enforcement.RateLimitKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security filter for enforcing rate limits on gRPC requests.
 */
@Component
public class GrpcRateLimitFilter extends OncePerRequestFilter {
    @Autowired
    private RateLimitFactory rateLimitFactory;

    @Autowired
    private RateLimitKeyGenerator keyGenerator;

    /**
     * Filters incoming gRPC requests and enforces rate limits.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain
     * @throws ServletException if an error occurs during filtering
     * @throws IOException if an I/O error occurs during filtering
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String methodName = request.getRequestURI(); // Extract gRPC method name
        String key = generateKey(request); // Generate a unique key

        // Fetch rate limit configuration for the method (e.g., from application.yml)
        int limit = getRateLimitForMethod(methodName);
        int duration = getRateLimitDurationForMethod(methodName);

        RateLimitStrategy strategy = rateLimitFactory.getStrategy("guava"); // Use default strategy
        if (!strategy.allowRequest(key, limit, duration)) {
            response.setStatus(429);
            response.getWriter().write("Rate limit exceeded for gRPC method: " + methodName);
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Generates a unique key for the request.
     *
     * @param request the HTTP request
     * @return the generated key
     */
    private String generateKey(HttpServletRequest request) {
        // Implement key generation logic (e.g., user ID, IP, etc.)
        return "user-key"; // Placeholder
    }

    /**
     * Fetches the rate limit for the specified method.
     *
     * @param methodName the name of the gRPC method
     * @return the rate limit for the method
     */
    private int getRateLimitForMethod(String methodName) {
        // Fetch rate limit configuration for the method (e.g., from application.yml)
        return 10; // Placeholder
    }

    /**
     * Fetches the rate limit duration for the specified method.
     *
     * @param methodName the name of the gRPC method
     * @return the rate limit duration for the method
     */
    private int getRateLimitDurationForMethod(String methodName) {
        // Fetch rate limit duration for the method (e.g., from application.yml)
        return 60; // Placeholder
    }
}