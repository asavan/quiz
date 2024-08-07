package ya;

import java.util.HashMap;
import java.util.Map;

public class LFU3 {
    class Node<K, V> {
        private int freq;
        public final K key;
        private V value;
        public Node<K, V> next;
        public Node<K, V> prev;

        public Node() {
            this(null, null);
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
        public void increaseFreq() {
            ++freq;
        }
        public int getFreq() {
            return freq;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V v) {
            this.value = v;
        }
    }

    class DLList<K, V> {
        private final Node<K, V> head;
        public DLList() {
            head = new Node<K, V>();
            head.next = head;
            head.prev = head;
        }

        void addFirst(Node<K, V> n) {
            n.next = head.next;
            n.prev = head;
            n.next.prev = n;
            head.next = n;
        }
        void delete(Node<K, V> n) {
            n.next.prev = n.prev;
            n.prev.next = n.next;
            n.prev = n.next = null;
        }

        Node<K, V> removeLast() {
            Node<K, V> last = head.prev;
            delete(last);
            return last;
        }

        public boolean isEmpty() {
            return head == head.next;
        }
    }

    class LFUCache {
        private final Map<Integer, Node<Integer, Integer>> map;
        private final Map<Integer, DLList<Integer, Integer>> freqMap;
        private int minFreq;
        private final int capacity;

        public LFUCache(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Bad capacity");
            }
            this.map = new HashMap<>();
            this.freqMap = new HashMap<>();
            this.minFreq = 0;
            this.capacity = capacity;
        }

        public int get(int k) {
            var n = map.get(k);
            if (n == null) {
                return -1;
            }
            updateFreq(n);
            return n.getValue();
        }

        public void put(int k, int v) {
            var n = map.get(k);
            if (n != null) {
                updateFreq(n);
                n.setValue(v);
                return;
            }
            if (capacity == map.size()) {
                var list = freqMap.get(minFreq);
                var old = list.removeLast();
                if (list.isEmpty() && old.getFreq() != 1) {
                    freqMap.remove(old.getFreq());
                }
                map.remove(old.key);
            }
            var newNode = new Node<>(k, v);
            minFreq = 1;
            freqMap.computeIfAbsent(minFreq, _k -> new DLList<>()).addFirst(newNode);
            map.put(k, newNode);
        }

        private void updateFreq(Node<Integer, Integer> n) {
            var list = freqMap.get(n.getFreq());
            list.delete(n);
            if (list.isEmpty()) {
                if (minFreq == n.getFreq()) {
                    ++minFreq;
                }
                freqMap.remove(n.getFreq());
            }
            n.increaseFreq();
            freqMap.computeIfAbsent(n.getFreq(), _k -> new DLList<>()).addFirst(n);
        }

        public void print() {
            for (var a : map.entrySet()) {
                System.out.println(a.getKey() + " " + a.getValue().getValue());
            }
            System.out.println();
        }
    }

}
