package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    int limit = 10;
    long millis = 60000L;//1min

    Map<String, Deque<Long>> trackRequests = new ConcurrentHashMap<>();

    @Override
    public void run(String... args) throws Exception {


    }

    boolean allow(String ipAddress) {
        long now = System.currentTimeMillis();
        if (!trackRequests.containsKey(ipAddress)) {
            trackRequests.put(ipAddress, new ArrayDeque<>());
        }
        Deque<Long> dq = trackRequests.get(ipAddress);

        synchronized (dq) {
            while (!dq.isEmpty() && now - dq.peekFirst() > millis) {
                dq.pollFirst();
            }
            if (dq.size() <= limit) {
                dq.add(now);
                return true;
            }
            return false;
        }

    }
}