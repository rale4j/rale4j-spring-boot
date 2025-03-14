package com.rale4j.sb.graphql;

import com.rale4j.sb.annotation.Rale4j;
import org.springframework.stereotype.Component;

/**
 * Example GraphQL resolver with rate-limiting annotations.
 */
@Component
public class GraphQLRateLimitResolver {
    @Rale4j(strategy = "guava", limit = 10, duration = 60)
    public String getResource() {
        return "GraphQL resource data";
    }
}