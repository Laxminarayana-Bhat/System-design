package org.example.rate;

public interface RateLimiterInterface {
    boolean allowRequest(String clientId);
}
