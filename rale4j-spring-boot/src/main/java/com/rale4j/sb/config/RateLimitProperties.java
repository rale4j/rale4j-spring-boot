package com.rale4j.sb.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for rate limiting in the Rale4j application.
 */
@Component
@ConfigurationProperties(prefix = "rale4j")
public class RateLimitProperties {
    private Strategies strategies;
    private Limits limits;
    private Security security;
    private Observability observability;

    /**
     * Gets the strategies configuration.
     *
     * @return the strategies configuration
     */
    public Strategies getStrategies() {
        return strategies;
    }

    /**
     * Sets the strategies configuration.
     *
     * @param strategies the strategies configuration
     */
    public void setStrategies(Strategies strategies) {
        this.strategies = strategies;
    }

    /**
     * Gets the limits configuration.
     *
     * @return the limits configuration
     */
    public Limits getLimits() {
        return limits;
    }

    /**
     * Sets the limits configuration.
     *
     * @param limits the limits configuration
     */
    public void setLimits(Limits limits) {
        this.limits = limits;
    }

    /**
     * Gets the security configuration.
     *
     * @return the security configuration
     */
    public Security getSecurity() {
        return security;
    }

    /**
     * Sets the security configuration.
     *
     * @param security the security configuration
     */
    public void setSecurity(Security security) {
        this.security = security;
    }

    /**
     * Gets the observability configuration.
     *
     * @return the observability configuration
     */
    public Observability getObservability() {
        return observability;
    }

    /**
     * Sets the observability configuration.
     *
     * @param observability the observability configuration
     */
    public void setObservability(Observability observability) {
        this.observability = observability;
    }

    /**
     * Configuration for different rate limiting strategies.
     */
    public static class Strategies {
        private Guava guava;
        private Redis redis;
        private TokenBucket tokenBucket;
        private Resilience4j resilience4j;
        private Bucket4j bucket4j;

        /**
         * Gets the Guava rate limiting strategy configuration.
         *
         * @return the Guava rate limiting strategy configuration
         */
        public Guava getGuava() {
            return guava;
        }

        /**
         * Sets the Guava rate limiting strategy configuration.
         *
         * @param guava the Guava rate limiting strategy configuration
         */
        public void setGuava(Guava guava) {
            this.guava = guava;
        }

        /**
         * Gets the Redis rate limiting strategy configuration.
         *
         * @return the Redis rate limiting strategy configuration
         */
        public Redis getRedis() {
            return redis;
        }

        /**
         * Sets the Redis rate limiting strategy configuration.
         *
         * @param redis the Redis rate limiting strategy configuration
         */
        public void setRedis(Redis redis) {
            this.redis = redis;
        }

        /**
         * Gets the TokenBucket rate limiting strategy configuration.
         *
         * @return the TokenBucket rate limiting strategy configuration
         */
        public TokenBucket getTokenBucket() {
            return tokenBucket;
        }

        /**
         * Sets the TokenBucket rate limiting strategy configuration.
         *
         * @param tokenBucket the TokenBucket rate limiting strategy configuration
         */
        public void setTokenBucket(TokenBucket tokenBucket) {
            this.tokenBucket = tokenBucket;
        }

        /**
         * Gets the Resilience4j rate limiting strategy configuration.
         *
         * @return the Resilience4j rate limiting strategy configuration
         */
        public Resilience4j getResilience4j() {
            return resilience4j;
        }

        /**
         * Sets the Resilience4j rate limiting strategy configuration.
         *
         * @param resilience4j the Resilience4j rate limiting strategy configuration
         */
        public void setResilience4j(Resilience4j resilience4j) {
            this.resilience4j = resilience4j;
        }

        /**
         * Gets the Bucket4j rate limiting strategy configuration.
         *
         * @return the Bucket4j rate limiting strategy configuration
         */
        public Bucket4j getBucket4j() {
            return bucket4j;
        }

        /**
         * Sets the Bucket4j rate limiting strategy configuration.
         *
         * @param bucket4j the Bucket4j rate limiting strategy configuration
         */
        public void setBucket4j(Bucket4j bucket4j) {
            this.bucket4j = bucket4j;
        }
    }

    /**
     * Configuration for rate limiting limits.
     */
    public static class Limits {
        private Default defaultLimit;
        private Metrics metrics;

        /**
         * Gets the default rate limiting configuration.
         *
         * @return the default rate limiting configuration
         */
        public Default getDefaultLimit() {
            return defaultLimit;
        }

        /**
         * Sets the default rate limiting configuration.
         *
         * @param defaultLimit the default rate limiting configuration
         */
        public void setDefaultLimit(Default defaultLimit) {
            this.defaultLimit = defaultLimit;
        }

        /**
         * Gets the metrics rate limiting configuration.
         *
         * @return the metrics rate limiting configuration
         */
        public Metrics getMetrics() {
            return metrics;
        }

        /**
         * Sets the metrics rate limiting configuration.
         *
         * @param metrics the metrics rate limiting configuration
         */
        public void setMetrics(Metrics metrics) {
            this.metrics = metrics;
        }
    }

    /**
     * Configuration for security settings.
     */
    public static class Security {
        private OAuth2ResourceServerProperties.Jwt jwt;
        private OAuth2 oauth2;
        private ApiKey apiKey;
        private Rbac rbac;

        /**
         * Gets the JWT security configuration.
         *
         * @return the JWT security configuration
         */
        public OAuth2ResourceServerProperties.Jwt getJwt() {
            return jwt;
        }

        /**
         * Sets the JWT security configuration.
         *
         * @param jwt the JWT security configuration
         */
        public void setJwt(OAuth2ResourceServerProperties.Jwt jwt) {
            this.jwt = jwt;
        }

        /**
         * Gets the OAuth2 security configuration.
         *
         * @return the OAuth2 security configuration
         */
        public OAuth2 getOauth2() {
            return oauth2;
        }

        /**
         * Sets the OAuth2 security configuration.
         *
         * @param oauth2 the OAuth2 security configuration
         */
        public void setOauth2(OAuth2 oauth2) {
            this.oauth2 = oauth2;
        }

        /**
         * Gets the API key security configuration.
         *
         * @return the API key security configuration
         */
        public ApiKey getApiKey() {
            return apiKey;
        }

        /**
         * Sets the API key security configuration.
         *
         * @param apiKey the API key security configuration
         */
        public void setApiKey(ApiKey apiKey) {
            this.apiKey = apiKey;
        }

        /**
         * Gets the RBAC security configuration.
         *
         * @return the RBAC security configuration
         */
        public Rbac getRbac() {
            return rbac;
        }

        /**
         * Sets the RBAC security configuration.
         *
         * @param rbac the RBAC security configuration
         */
        public void setRbac(Rbac rbac) {
            this.rbac = rbac;
        }
    }

    /**
     * Configuration for observability settings.
     */
    public static class Observability {
        private Prometheus prometheus;
        private Grafana grafana;

        /**
         * Gets the Prometheus observability configuration.
         *
         * @return the Prometheus observability configuration
         */
        public Prometheus getPrometheus() {
            return prometheus;
        }

        /**
         * Sets the Prometheus observability configuration.
         *
         * @param prometheus the Prometheus observability configuration
         */
        public void setPrometheus(Prometheus prometheus) {
            this.prometheus = prometheus;
        }

        /**
         * Gets the Grafana observability configuration.
         *
         * @return the Grafana observability configuration
         */
        public Grafana getGrafana() {
            return grafana;
        }

        /**
         * Sets the Grafana observability configuration.
         *
         * @param grafana the Grafana observability configuration
         */
        public void setGrafana(Grafana grafana) {
            this.grafana = grafana;
        }
    }

    /**
     * Configuration for the Guava rate limiting strategy.
     */
    public static class Guava {
        private boolean enabled;

        /**
         * Checks if the Guava rate limiting strategy is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the Guava rate limiting strategy enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * Configuration for the Redis rate limiting strategy.
     */
    public static class Redis {
        private boolean enabled;
        private String host;
        private int port;

        /**
         * Checks if the Redis rate limiting strategy is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the Redis rate limiting strategy enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        /**
         * Gets the Redis host.
         *
         * @return the Redis host
         */
        public String getHost() {
            return host;
        }

        /**
         * Sets the Redis host.
         *
         * @param host the Redis host
         */
        public void setHost(String host) {
            this.host = host;
        }

        /**
         * Gets the Redis port.
         *
         * @return the Redis port
         */
        public int getPort() {
            return port;
        }

        /**
         * Sets the Redis port.
         *
         * @param port the Redis port
         */
        public void setPort(int port) {
            this.port = port;
        }
    }

    /**
     * Configuration for the TokenBucket rate limiting strategy.
     */
    public static class TokenBucket {
        private boolean enabled;

        /**
         * Checks if the TokenBucket rate limiting strategy is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the TokenBucket rate limiting strategy enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * Configuration for the Resilience4j rate limiting strategy.
     */
    public static class Resilience4j {
        private boolean enabled;

        /**
         * Checks if the Resilience4j rate limiting strategy is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the Resilience4j rate limiting strategy enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * Configuration for the Bucket4j rate limiting strategy.
     */
    public static class Bucket4j {
        private boolean enabled;

        /**
         * Checks if the Bucket4j rate limiting strategy is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the Bucket4j rate limiting strategy enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * Default rate limiting configuration.
     */
    public static class Default {
        private int limit;
        private int duration;

        /**
         * Gets the default rate limit.
         *
         * @return the default rate limit
         */
        public int getLimit() {
            return limit;
        }

        /**
         * Sets the default rate limit.
         *
         * @param limit the default rate limit
         */
        public void setLimit(int limit) {
            this.limit = limit;
        }

        /**
         * Gets the default rate limit duration.
         *
         * @return the default rate limit duration
         */
        public int getDuration() {
            return duration;
        }

        /**
         * Sets the default rate limit duration.
         *
         * @param duration the default rate limit duration
         */
        public void setDuration(int duration) {
            this.duration = duration;
        }
    }

    /**
     * Metrics rate limiting configuration.
     */
    public static class Metrics {
        private int limit;
        private int duration;

        /**
         * Gets the metrics rate limit.
         *
         * @return the metrics rate limit
         */
        public int getLimit() {
            return limit;
        }

        /**
         * Sets the metrics rate limit.
         *
         * @param limit the metrics rate limit
         */
        public void setLimit(int limit) {
            this.limit = limit;
        }

        /**
         * Gets the metrics rate limit duration.
         *
         * @return the metrics rate limit duration
         */
        public int getDuration() {
            return duration;
        }

        /**
         * Sets the metrics rate limit duration.
         *
         * @param duration the metrics rate limit duration
         */
        public void setDuration(int duration) {
            this.duration = duration;
        }
    }

    /**
     * JWT security configuration.
     */
    public static class Jwt {
        private boolean enabled;

        /**
         * Checks if JWT security is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the JWT security enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * OAuth2 security configuration.
     */
    public static class OAuth2 {
        private boolean enabled;

        /**
         * Checks if OAuth2 security is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the OAuth2 security enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * API key security configuration.
     */
    public static class ApiKey {
        private boolean enabled;

        /**
         * Checks if API key security is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the API key security enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * RBAC security configuration.
     */
    public static class Rbac {
        private boolean enabled;

        /**
         * Checks if RBAC security is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the RBAC security enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * Prometheus observability configuration.
     */
    public static class Prometheus {
        private boolean enabled;

        /**
         * Checks if Prometheus observability is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the Prometheus observability enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * Grafana observability configuration.
     */
    public static class Grafana {
        private boolean enabled;

        /**
         * Checks if Grafana observability is enabled.
         *
         * @return true if enabled, false otherwise
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the Grafana observability enabled state.
         *
         * @param enabled the enabled state
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}