package org.example.ratelimiter;

public interface RateLimiterInterface {
    boolean allowRequest(String clientId);
}
