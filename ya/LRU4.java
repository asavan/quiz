package ya;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asavan on 26.04.2020.
 */
public class LRU4 {
    static class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value){
            this.key=key;
            this.value=value;
        }
    }

    static class LRUCache {
        Node head;
        Node tail;
        Map<Integer, Node> map = null;
        int cap = 0;

        public LRUCache(int capacity) {
            this.cap = capacity;
            this.map = new HashMap<>();
        }

        public int get(int key) {
            if(map.get(key)==null){
                return -1;
            }

            //move to tail
            Node t = map.get(key);

            removeNode(t);
            offerNode(t);

            return t.value;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                Node t = map.get(key);
                t.value = value;

                //move to tail
                removeNode(t);
                offerNode(t);
            }else{
                if(map.size()>=cap){
                    //delete head
                    map.remove(head.key);
                    removeNode(head);
                }

                //add to tail
                Node node = new Node(key, value);
                offerNode(node);
                map.put(key, node);
            }
        }

        private void removeNode(Node n){
            if(n.prev!=null){
                n.prev.next = n.next;
            }else{
                head = n.next;
            }

            if(n.next!=null){
                n.next.prev = n.prev;
            }else{
                tail = n.prev;
            }
        }

        private void offerNode(Node n){
            if(tail!=null){
                tail.next = n;
            }

            n.prev = tail;
            n.next = null;
            tail = n;

            if(head == null){
                head = tail;
            }
        }

        public void print() {
            for (Map.Entry<Integer, Node> integerEntryEntry : map.entrySet()) {
                System.out.println(integerEntryEntry.getKey() + " " + integerEntryEntry.getValue().value);
            }
        }

    }
    public static void main(String[] args) {
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
