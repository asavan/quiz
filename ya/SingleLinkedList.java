package ya;

/**
 * Created by asavan on 05.02.2021.
 */
public class SingleLinkedList {
    static class Node {
        public int value;
        public Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        Node list = new Node(1, new Node(2, new Node(3, null)));
        print(list);
        Node reversed = reverseList(list);
        print(reversed);
    }

    public static Node reverseList(Node node) {
        Node current = node;
        Node prev = null;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void print(Node node) {
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println("");
    }
}
