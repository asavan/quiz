package ya;

import java.util.HashMap;
import java.util.Map;

public class LFU5 {
    class Node {
        public Node next;
        public Node prev;
        public final int key;
        public int value;
        public int count;
        public Node(int k, int v) {
            this.key = k;
            this.value = v;
            this.count = 1;
        }
        public Node() {
            this(-1, -1);
        }
    }

    class DLList {
        Node fake;
        public DLList() {
            fake = new Node();
            fake.prev = fake.next = fake;
        }
        void addFirst(Node n) {
            n.next = fake.next;
            n.prev = fake;
            fake.next.prev = n;
            fake.next = n;
        }
        void delete(Node n) {
            n.next.prev = n.prev;
            n.prev.next = n.next;
            n.prev = n.next = null;
        }

        Node deleteLast() {
            var last = fake.prev;
            delete(last);
            return last;
        }

        boolean isEmpty() {
            return fake == fake.next;
        }
    }

    class LFUCache {
        final Map<Integer, Node> map;
        final Map<Integer, DLList> freq;
        final int capacity;
        int minFreq;
        public LFUCache(int capacity) {
            map = new HashMap<>();
            freq = new HashMap<>();
            this.capacity = capacity;
            minFreq = 0;
        }

        private void updateFreq(Node n) {
            var list = freq.get(n.count);
            list.delete(n);
            if (list.isEmpty()) {
                freq.remove(n.count);
                if (minFreq == n.count) {
                    ++minFreq;
                }
            }
            ++n.count;
            freq.computeIfAbsent(n.count, _k -> new DLList()).addFirst(n);
        }

        public int get(int key) {
            var n = map.get(key);
            if (n == null) {
                return -1;
            }
            updateFreq(n);
            return n.value;
        }

        public void put(int key, int value) {
            var n = map.get(key);
            if (n != null) {
                updateFreq(n);
                n.value = value;
                return;
            }
            if (map.size() == capacity) {
                var list = freq.get(minFreq);
                var last = list.deleteLast();
                if (list.isEmpty() && minFreq != 1) {
                    freq.remove(minFreq);
                }
                map.remove(last.key);
            }
            var newNode = new Node(key, value);
            minFreq = newNode.count;
            freq.computeIfAbsent(newNode.count, _k -> new DLList()).addFirst(newNode);
            map.put(key, newNode);
        }
    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
