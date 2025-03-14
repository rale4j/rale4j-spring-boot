package com.rale4j.sb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to enforce rate limiting on methods.
 * <p>
 * Example usage:
 * <pre>
 * {@code @Rale4j(strategy = "guava", limit = 10, duration = 60)}
 * public String myMethod() {
 *     return "Rate-limited method";
 * }
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Rale4j {
    /**
     * The rate-limiting strategy to use (e.g., "guava", "redis").
     *
     * @return the strategy name
     */
    String strategy() default "guava";

    /**
     * The maximum number of requests allowed within the duration.
     *
     * @return the request limit
     */
    int limit() default 10;

    /**
     * The time window (in seconds) for the rate limit.
     *
     * @return the duration in seconds
     */
    int duration() default 60;
}