package ya;

import java.util.HashMap;
import java.util.Map;

public class LRU9 {
    static class Node<K, V> {
        public final K key;
        public V value;
        public Node<K, V> next;
        public Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public Node() {
            this(null, null);
        }
    }
    static class DLList<K, V> {
        private final Node<K, V> head;
        public DLList() {
            head = new Node<>();
            head.next = head;
            head.prev = head;
        }
        public void addFirst(Node<K, V> n) {
            n.next = head.next;
            n.prev = head;
            n.next.prev = n;
            head.next = n;
        }
        public void deleteNode(Node<K, V> n) {
            n.next.prev = n.prev;
            n.prev.next = n.next;
        }
        public Node<K, V> removeLast() {
            Node<K, V> last = head.prev;
            deleteNode(last);
            return last;
        }
    }
    static class LRUCache {
        private final int capacity;
        private final Map<Integer, Node<Integer, Integer>> map;
        private final DLList<Integer, Integer> list;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            list = new DLList<>();
        }

        private void touch(Node<Integer, Integer> n) {
            list.deleteNode(n);
            list.addFirst(n);
        }

        public int get(int k) {
            var n = map.get(k);
            if (n == null) {
                return -1;
            }
            touch(n);
            return n.value;
        }

        public void put(int k, int v) {
            var n = map.get(k);
            if (n != null) {
                n.value = v;
                touch(n);
                return;
            }
            if (map.size() == capacity) {
                var last = list.removeLast();
                map.remove(last.key);
            }
            var newNode = new Node<>(k, v);
            list.addFirst(newNode);
            map.put(k, newNode);
        }

        public void print() {
            for (var entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue().value);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // your code goes here
        LRUCache lrucache = new LRUCache(4);
        lrucache.put(1, 1);
        lrucache.put(10, 15);
        lrucache.put(15, 10);
        lrucache.put(10, 16);
        lrucache.put(12, 15);
        lrucache.put(18, 10);
        lrucache.put(13, 16);

        System.out.println(lrucache.get(1));
        System.out.println(lrucache.get(10));
        System.out.println(lrucache.get(15));
        lrucache.print();

    }

}
