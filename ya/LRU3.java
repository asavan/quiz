package ya;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by asavan on 26.04.2020.
 */
public class LRU3 {
    static class Node {
        int value;
        int key;
        Node left;
        Node right;
    }
    public static class LRUCache {

        Map<Integer, Node> hashmap;
        Node start, end;
        int LRU_SIZE;
        // implementation, it can make be dynamic
        public LRUCache(int size) {
            LRU_SIZE = size;
            hashmap = new HashMap<>();
        }

        public int getEntry(int key) {
            if (hashmap.containsKey(key)) // Key Already Exist, just update the
            {
                Node entry = hashmap.get(key);
                removeNode(entry);
                addAtTop(entry);
                return entry.value;
            }
            return -1;
        }

        public void putEntry(int key, int value) {
            if (hashmap.containsKey(key)) // Key Already Exist, just update the value and move it to top
            {
                Node entry = hashmap.get(key);
                entry.value = value;
                removeNode(entry);
                addAtTop(entry);
            } else {
                Node newnode = new Node();
                newnode.left = null;
                newnode.right = null;
                newnode.value = value;
                newnode.key = key;
                if (hashmap.size() >= LRU_SIZE) // We have reached maxium size so need to make room for new element.
                {
                    hashmap.remove(end.key);
                    removeNode(end);
                    // addAtTop(newnode);

                }
                addAtTop(newnode);
                hashmap.put(key, newnode);
            }
        }
        public void addAtTop(Node node) {
            node.right = start;
            node.left = null;
            if (start != null)
                start.left = node;
            start = node;
            if (end == null)
                end = start;
        }

        public void removeNode(Node node) {

            if (node.left != null) {
                node.left.right = node.right;
            } else {
                start = node.right;
            }

            if (node.right != null) {
                node.right.left = node.left;
            } else {
                end = node.left;
            }
        }

        public void print() {
            for (Map.Entry<Integer, Node> entry : hashmap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue().value);
            }
        }
    }
    public static void main(String[] args) throws java.lang.Exception {
        // your code goes here
        LRUCache lrucache = new LRUCache(4);
        lrucache.putEntry(1, 1);
        lrucache.putEntry(10, 15);
        lrucache.putEntry(15, 10);
        lrucache.putEntry(10, 16);
        lrucache.putEntry(12, 15);
        lrucache.putEntry(18, 10);
        lrucache.putEntry(13, 16);

        System.out.println(lrucache.getEntry(1));
        System.out.println(lrucache.getEntry(10));
        System.out.println(lrucache.getEntry(15));

        lrucache.print();
    }
}
