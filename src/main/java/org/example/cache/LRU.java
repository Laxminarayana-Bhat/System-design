package org.example.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRU {

//    Integer size;
//    Map<Integer, Integer> map;


    int capacity;
    Map<Integer, Node> map;

    private Node head;
    private Node tail;


    static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    public LRU(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;

        //required to remove and add head easily
        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    // if exist move to head and return val, else -1
    public int get(int key) {
        if (map.get(key) == null) {
            return -1;
        }
        Node ele = map.get(key);
        moveToHead(ele);
        return ele.value;
    }

    //2 scenarios, if key present and if not and also if size > capacity remove from tail
    public void put(int key, int value) {
        //if present just change the value and move to head
        if (map.get(key) != null) {
            Node ele = map.get(key);
            ele.value = value;
            moveToHead(ele);
            return;
        }

        //if not present
        Node newNode = new Node(key, value);
        map.put(key, newNode);
        newNodeToHead(newNode);

        // if size is more remove tail and
        if (map.size() > capacity) {
            Node lru = removeTail();
            map.remove(lru.key);//dont forgot to remove key
        }

    }

    public void moveToHead(Node ele) {
        removeNode(ele);
        newNodeToHead(ele);
    }

    //add new node to head to maintain new ele at beginning
    public void newNodeToHead(Node ele) {
        ele.next = head.next;
        ele.prev = head;

        head.next.prev = ele;
        head.next = ele;
    }

    //remove any node
    public void removeNode(Node ele) {
        ele.prev.next = ele.next;
        ele.next.prev = ele.prev;
    }

    //remove the lru ele
    public Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }

//    public LRU(int capacity) {
//        this.size = capacity;
//        map = new LinkedHashMap<>(7, 0.75F, true) {
//            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//                return size() > capacity;
//            }
//        };
//    }
//
//    public int get(int key) {
//        if (map.get(key) == null) {
//            return -1;
//        } else {
//            return map.get(key);
//        }
//    }
//
//    public void put(int key, int value) {
//        map.put(key,value);
//    }


}
