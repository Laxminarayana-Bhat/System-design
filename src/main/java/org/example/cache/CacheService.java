package org.example.cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CacheService {

    //Steps
    /*
    need map<k,CustCache> and a CustCache class with k,v,ttl and a PQ<CustCache> with comparator
    will have put, get method
    in get - method evict() is called and if didnt get return null
    in put - set current+ttl as expiry to Cust and put that in map and PQ
    evict() - if q is not empty and q.peek.ttl <= current -> eligible to remove ->

     */
    Map<String, CacheEntry> cacheMap = new HashMap<>();

    PriorityQueue<CacheEntry> ttlQueue = new PriorityQueue<>(Comparator.comparingLong(e -> e.ttl));


    public String get(String key) {
        evict();

        return cacheMap.get(key) == null ? null : cacheMap.get(key).value;
    }

    public void put(String k, String v, Long ttl) {
        long expiry = System.currentTimeMillis() + ttl; // current + ttl
        CacheEntry cacheEntry = new CacheEntry(k, v, expiry);
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
            /*
            put("A", value1, 1 sec)
            put("A", value2, 5 sec)
            What happens internally?
            Heap contains → entry1 (1 sec), entry2 (5 sec)

            Map contains → only entry2 (latest value)

            After 1 second:

            entry1 expires

            It comes out of the heap

            Now the danger:

            If we directly do:

            cacheMap.remove("A");
            We will remove value2 (new value) — which is wrong.
             */
        }
    }
}
