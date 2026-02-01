package org.example.cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CacheService {

    Map<String, CacheEntry> cacheMap = new HashMap<>();

    PriorityQueue<CacheEntry> ttlQueue = new PriorityQueue<>(Comparator.comparingLong(e -> e.ttl));


    public String get(String key) {
        evict();

        return cacheMap.get(key) == null ? null : cacheMap.get(key).value;
    }

    public void put(String k, String v, Long ttl) {
        long expiry = System.currentTimeMillis() + ttl;

        CacheEntry cacheEntry = new CacheEntry(k, v, ttl);
        cacheMap.put(k, cacheEntry);
        ttlQueue.offer(cacheEntry);
    }

    public void evict() {
        long current = System.currentTimeMillis();
        while (!ttlQueue.isEmpty() && ttlQueue.peek().ttl <= current) {
            CacheEntry expired = ttlQueue.poll();

            // IMPORTANT: ensure it’s the latest entry
            CacheEntry currentEntry = cacheMap.get(expired.key);
            if (currentEntry == expired) {
                cacheMap.remove(expired.key);
            }
        }
    }
}
