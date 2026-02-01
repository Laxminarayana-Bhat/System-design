package org.example.rate;

import java.util.Map;
import java.util.concurrent.*;

public class BlockingQueueImpl implements RateLimiterInterface {

    int limit = 10;
    long refillRate = 60000L;
    private final Map<String, BlockingQueue<Integer>> q = new ConcurrentHashMap<>();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    // create a blocking queue with cloentid
    @Override
    public boolean allowRequest(String clientId) {
        BlockingQueue<Integer> blockingQueue = q.computeIfAbsent(clientId, x -> new ArrayBlockingQueue<>(limit));

        if (blockingQueue.offer(1)) {
            executorService.schedule(() -> blockingQueue.poll(), refillRate, TimeUnit.MILLISECONDS);
            return true;
        }
        return false;
    }
}
