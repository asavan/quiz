package ya;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Wrong solution
 */
public class LRU6 {

    public static class LruCache<K, V> {
        private final Map<K, ListNode<K, V>> map;
        private final int capacity;
        private K first;
        private K last;

        public LruCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
        }

        /**
         * @return True if the cache is currently empty, false otherwise
         */
        public boolean isEmpty() {
            return map.isEmpty();
        }

        /**
         * @return Number of items in the cache
         */
        public int size() {
            return map.size();
        }

        /**
         * @param key Key
         * @return True if the cache contains an items corresponding to the given key, false otherwise
         */
        public boolean contains(K key) {
            return map.containsKey(key);
        }

        /**
         * @param key Key
         * @return Associated value, if the item is present in the cache
         */
        public Optional<V> get(K key) {
            if (contains(key)) {
                return set(key, map.get(key).value);
            }

            return Optional.empty();
        }

        /**
         * @param key Key
         * @return Associated value, if the item was present in the cache
         */
        public Optional<V> delete(K key) {
            if (!contains(key)) {
                return Optional.empty();
            }

            ListNode<K, V> entry = map.remove(key);

            if (last == first) {
                first = null;
                last = null;
            } else if (key == first) {
                first = map.get(first).next;
            } else {
                map.get(entry.prev).next = entry.next;
            }
            return Optional.of(entry.value);
        }

        /**
         * @param key   Key
         * @param value Value
         * @return Previous value, if the item was present in the cache
         */
        public Optional<V> set(K key, V value) {
            if (value == null) {
                throw new IllegalArgumentException("Value must not be null");
            }
            Optional<V> old = Optional.empty();
            System.out.println("1 " + size());
            if (contains(key)) {
                old = delete(key);
            } else if (size() == capacity) {
                System.out.println("2 " + size());
                delete(last);
                System.out.println("3 " + size());
            }

            ListNode<K, V> entry = new ListNode<>(null, first, value);
            if (isEmpty()) {
                last = key;
            } else {
                map.get(first).prev = key;
            }
            first = key;
            System.out.println("4 " + size());
            map.put(key, entry);
            System.out.println("5 " + size());

            return old;
        }

        public Optional<V> put(K key, V value) {
            return set(key, value);
        }

        public void print() {
            for (Map.Entry<K, ListNode<K, V>> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue().value);
            }
            System.out.println("");
        }

        private static class ListNode<K, V> {
            private K prev;
            private K next;
            private V value;

            private ListNode(K prev, K next, V value) {
                this.prev = prev;
                this.next = next;
                this.value = value;
            }
        }
    }

    public static void main(String[] args) {
        // your code goes here
        LruCache<Integer, Integer> lrucache = new LruCache<>(4);
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
        System.out.println(lrucache.size());
        lrucache.print();

    }
}
