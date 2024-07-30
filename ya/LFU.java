package ya;

import java.util.HashMap;
import java.util.Map;

// http://dhruvbird.com/lfu.pdf
// https://leetcode.com/problems/lfu-cache/solutions/94547/java-o-1-solution-using-two-hashmap-and-one-doublelinkedlist/



public class LFU {
    static class Node {
        private int freq;
        public final int key;
        public int value;
        public Node prev;
        public Node next;
        public void increaseFreq()  {
            ++freq;
        }
        public Node(int key, int val) {
            this.key = key;
            this.value = val;
            this.freq = 1;
        }
    }

    static class DLList {
        private final Node head;
        private final Node tail;
        int size;
        public DLList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.next = head;
            size = 0;
        }
        public void addFirst(Node n) {
            n.next = head.next;
            n.prev = head;
            n.next.prev = n;
            head.next = n;
            ++size;
        }

        public void delete(Node n) {
            n.next.prev = n.prev;
            n.prev.next = n.next;
            n.prev = n.next = null;
            --size;
        }
        public Node removeLast() {
            Node last = tail.prev;
            delete(last);
            return last;
        }
        public boolean isEmpty() {
            return size == 0;
        }
    }

    static class LFUCache {
        private final Map<Integer, Node> map;
        private final Map<Integer, DLList> freqMap;
        private int minFreq = 0;
        private final int capacity;

        public LFUCache(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Bad capacity");
            }
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.freqMap = new HashMap<>();
        }
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node n = map.get(key);
            updateFreq(n);
            return n.value;
        }
        public void put(int k, int v) {
            Node n = map.get(k);
            if (n != null) {
                updateFreq(n);
                n.value = v;
                return;
            }
            if (map.size() >= capacity) {
                var list = freqMap.get(minFreq);
                Node old = list.removeLast();
                map.remove(old.key);
                if (list.isEmpty() && old.freq != 1) {
                    freqMap.remove(old.freq);
                }
            }

            Node node = new Node(k, v);
            minFreq = node.freq;
            freqMap.computeIfAbsent(minFreq, _k -> new DLList()).addFirst(node);
            map.put(k, node);
        }
        private void updateFreq(Node n) {
            var list = freqMap.get(n.freq);
            list.delete(n);
            if (list.isEmpty()) {
                if (n.freq == minFreq) {
                    ++minFreq;
                }
                freqMap.remove(n.freq);
            }
            n.increaseFreq();
            freqMap.computeIfAbsent(n.freq, _k -> new DLList()).addFirst(n);
        }

        public void print() {
            for (var a : map.entrySet()) {
                System.out.println(a.getKey() + " " + a.getValue().value);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        var cache = new LFUCache(4);
        cache.put(1, 1);
        cache.put(10, 15);
        cache.put(15, 10);
        cache.put(10, 16);
        cache.put(12, 15);
        cache.put(18, 10);
        cache.put(13, 16);

        System.out.println(cache.get(1));
        System.out.println(cache.get(10));
        System.out.println(cache.get(15));
        cache.print();

    }
}
