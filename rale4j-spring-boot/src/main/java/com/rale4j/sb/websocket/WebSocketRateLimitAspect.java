package com.rale4j.sb.websocket;

import com.rale4j.sb.annotation.Rale4j;
import com.rale4j.sb.core.RateLimitStrategy;
import com.rale4j.sb.core.RateLimitFactory;
import com.rale4j.sb.enforcement.RateLimitKeyGenerator;
import com.rale4j.sb.exception.RateLimitExceededException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Aspect for enforcing rate limits on WebSocket messages.
 */
@Aspect
@Component
public class WebSocketRateLimitAspect {
    @Autowired
    private RateLimitFactory rateLimitFactory;

    @Autowired
    private RateLimitKeyGenerator keyGenerator;

    /**
     * Intercepts WebSocket message handlers annotated with {@link Rale4j} and enforces rate limits.
     *
     * @param joinPoint the join point representing the intercepted method
     * @param rale4j    the {@link Rale4j} annotation
     * @return the result of the intercepted method
     * @throws Throwable if the rate limit is exceeded or an error occurs
     */
    @Around("@annotation(rale4j)")
    public Object enforceRateLimit(ProceedingJoinPoint joinPoint, Rale4j rale4j) throws Throwable {
        // Generate a unique key for rate limiting
        String key = keyGenerator.generateKey(joinPoint);
        // Retrieve the rate-limiting strategy based on the annotation
        RateLimitStrategy strategy = rateLimitFactory.getStrategy(rale4j.strategy());

        // Check if the request is allowed based on the rate limit
        if (!strategy.allowRequest(key, rale4j.limit(), rale4j.duration())) {
            // Throw an exception if the rate limit is exceeded
            throw new RateLimitExceededException("Rate limit exceeded for WebSocket endpoint");
        }

        // Proceed with the intercepted method if the rate limit is not exceeded
        return joinPoint.proceed();
    }
}