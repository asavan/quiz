package meta.lca;

import org.junit.Test;

public class LCA {
    static class Node {
        public Node left, right, parent;
        public int value;
    }

    public static Node lca(Node a, Node b) {
        Node currentA = a;
        Node currentB = b;
        while (currentA != currentB) {
            if (currentA.parent != null) {
                currentA = currentA.parent;
            } else {
                currentA = b;
            }
            if (currentB.parent != null) {
                currentB = currentB.parent;
            } else {
                currentB = a;
            }
        }
        return currentA;
    }
}
