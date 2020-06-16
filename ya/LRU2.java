package ya;

import java.util.*;

/**
 * Created by asavan on 26.04.2020.
 *
 *
 * Wrong solution
 */
public class LRU2 {
    public static class LRUCache<K, V>{

        LinkedList<V> list;
        private Map<K, Iterator<V>> cache;
        private int maxSize;

        public LRUCache(int maxSize){
            this.maxSize = maxSize;
            list = new LinkedList<>();
            cache = new HashMap<>();
        }

        public V get(K key) {
            Iterator<V> it = cache.get(key);
            if (it == null) {
                return null;
            }
            V old_value = erase(key);
            put_(key, old_value);
            return old_value;
        }

        public void put_(K key, V value) {
            list.addFirst(value);
            Iterator<V> it = list.listIterator();
            cache.put(key, it);
        }

        public void put(K key, V value) {
            if (cache.get(key) != null) {
                erase(key);
            }
            if (cache.size() == maxSize) {
                // erase(list.peekFirst());
            }
            put_(key, value);
        }

        private V erase(K key) {
            Iterator<V> it = cache.remove(key);
            if (it == null) {
                throw new IllegalStateException("Not possible");
            }
            V value = it.next();
            it.remove();
            return value;
        }
    }

    public static void testStandart() {
        LRUCache<Integer, Integer> lrucache = new LRUCache<>(4);
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

    }

    public static void main(String[] args) {
        testStandart();
    }

    private static void test2() {
        LRUCache<Integer, Integer> lru = new LRUCache<>(5);
        lru.get(2);
        lru.put(1, 11);
        lru.put(2, 22);
        lru.get(2);
        lru.put(3, 33);
        lru.put(4, 44);
        lru.put(5, 55);
        lru.get(2);
        lru.get(1);
        lru.put(6, 66);
    }
}
