package ya;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by asavan on 26.04.2020.
 */
public class LRU7 {
    static class LRUCache {
        private int size;
        private final Map<Integer, Integer> map;
        public LRUCache(int _capacity) {
            // "true for access-order, false for insertion-order"
            map = new LinkedHashMap<Integer, Integer>(_capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    //must override it if used in a fixed cache
                    return this.size() > _capacity;
                }
            };
            size = _capacity;
        }
        public int get(int key) {
            Integer val = map.get(key);
            return val == null ? -1 : val;
        }
        void put(int key, int value) {
            map.put(key, value);
        }

    }
    public static void main(String[] args) throws java.lang.Exception {
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
        // lrucache.print();

    }
}
