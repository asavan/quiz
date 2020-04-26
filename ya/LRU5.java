package ya;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Wrong solution
 */
public class LRU5 {
    static class LRUCache {

        private final int capacity;

        private final Map<Integer, Node> map;

        private final LinkedList<Node> list;

        private static class Node {
            public int key;
            public int value;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            list = new LinkedList<>();
        }

        public int get(int key) {

            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            list.remove(node);
            list.addFirst(node);
            return map.get(key).value;
        }

        public void put(int key, int value) {
            if (list.size() == this.capacity) {
                Node node = list.removeLast();
                map.remove(node.key);
            }
            Node item = new Node(key, value);
            list.addFirst(item);
            map.put(key, item);
        }

        public void print() {
            for (Map.Entry<Integer, Node> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue().value);
            }
            for (Node node : list) {
                System.out.print(node.key + " -> " + node.value + " ");
            }
            System.out.println("");
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
        lrucache.print();

    }
}
