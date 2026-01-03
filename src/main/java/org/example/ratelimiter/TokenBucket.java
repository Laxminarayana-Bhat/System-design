package org.example.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucket implements RateLimiterInterface {
    // ===== CONFIG =====
    private static final int CAPACITY = 10;      // max tokens
    private static final int REFILL_RATE = 5;    // tokens per second

    // one bucket per client
    private final Map<String, BucketState> buckets = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(String clientId) {
        BucketState bucket = buckets.computeIfAbsent(
                clientId,
                id -> new BucketState(CAPACITY, System.currentTimeMillis())
        );

        synchronized (bucket) {
            refill(bucket);

            if (bucket.tokens > 0) {
                bucket.tokens--;
                return true;
            }
            return false;
        }
    }

    private void refill(BucketState bucket) {
        long now = System.currentTimeMillis();
        long elapsedMillis = now - bucket.lastRefillTime;//1020

        int tokensToAdd = (int) ((elapsedMillis / 1000.0) * REFILL_RATE);
        if (tokensToAdd > 0) {
            bucket.tokens = Math.min(CAPACITY, bucket.tokens + tokensToAdd);
            bucket.lastRefillTime = now;
        }
    }

    // ===== SIMPLE STATE (ONLY 2 PARAMS) =====
    private static class BucketState {
        int tokens;
        long lastRefillTime;

        BucketState(int tokens, long lastRefillTime) {
            this.tokens = tokens;
            this.lastRefillTime = lastRefillTime;
        }
    }
}
