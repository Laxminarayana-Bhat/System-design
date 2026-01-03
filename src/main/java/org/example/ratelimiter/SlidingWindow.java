package org.example.ratelimiter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindow implements RateLimiterInterface {
    private final int limit = 5;
    private final long windowSize = 60_000;//1 min

    private final Map<String, Deque<Long>> store = new HashMap<>();

    @Override
    public boolean allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        store.putIfAbsent(clientId, new ArrayDeque<>());
        Deque<Long> longs = store.get(clientId);
        while (!longs.isEmpty() && (now - longs.peekFirst()) > windowSize) {
            longs.pollFirst();
        }
        if (longs.size() <= limit) {
            longs.add(now);
            return true;
        }
        return false;
    }
}
