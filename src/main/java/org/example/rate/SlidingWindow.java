package org.example.rate;

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
        // if not exists add it
        store.putIfAbsent(clientId, new ArrayDeque<>());

        // get the deque by client id
        Deque<Long> longs = store.get(clientId);

        // while q is not empty and current time - the time in front of q > 60k millis
        // then remove that until we satisfy that condition
        while (!longs.isEmpty() && (now - longs.peekFirst()) > windowSize) {
            longs.pollFirst();
        }

        // after removing all other things, its size should be <= limit
        if (longs.size() <= limit) {
            longs.add(now);
            return true;
        }
        return false;
    }

//    [1232,12323,123213,122323,1223123]
}
