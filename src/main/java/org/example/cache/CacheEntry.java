package org.example.cache;

public class CacheEntry {
    public String key;
    public String value;
    public Long ttl;

    public CacheEntry(String key, String value, Long ttl) {
        this.key = key;
        this.value = value;
        this.ttl = ttl;
    }
}
