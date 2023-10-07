package ya;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Iterator;

public class LRU8 {
    static class LRUCache {

        private final int capacity;

        private final Map<Integer, Iterator<Node>> map;

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
            return updatePosition(key);
        }

        private int updatePosition(int key) {
            var nodeIter = map.get(key);
            Node val = nodeIter.next();
            nodeIter.remove();
            addToEnd(key, val);
            return val.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                updatePosition(key);
                return;
            }
            if (list.size() >= this.capacity) {
                Node node = list.removeFirst();
                map.remove(node.key);
            }
            Node item = new Node(key, value);
            addToEnd(key, item);
        }

        private void addToEnd(int key, Node item) {
            list.addLast(item);
            Iterator<Node> c = list.descendingIterator();
            map.put(key, c);
        }

        public void print() {
            for (Node node : list) {
                System.out.print(node.key + " -> " + node.value + " ");
            }
            System.out.println("");
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
