package ya.a2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by asavan on 07.02.2021.
 */
public class FindEquivalentSubtrees {
    static class Node {
        public char value;
        public Node left;
        public Node right;
        public Node() {
            left = null;
            right = null;
            value = 0;
        }

        public Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return value + " " +  super.toString();
        }
    }

    static class Pair {
        public Node first;
        public Node second;

        public Pair(Node first, Node second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        Node root = new Node('b', new Node('a', null, null), new Node('a', null, null));
        Pair res = findEquivalentSubtrees(root);
        if (res != null) {
            System.out.println(res.first);
            System.out.println(res.second);
        } else {
            System.out.println("Not found");
        }
    }

    public static Pair findEquivalentSubtrees(Node root) {
        Map<Integer, List<Box>> map = new HashMap<>();
        dfs(root, map);
        for (List<Box> boxes : map.values()) {
            if (boxes.size() >= 2) {
                return new Pair(boxes.get(0).node, boxes.get(1).node);
            }
        }
        return null;
    }

    static class Box {
        int descriptor;
        int size;
        Node node;

        public Box(Node node, int descriptor, int size) {
            this.node = node;
            this.descriptor = descriptor;
            this.size = size;
        }
    }
    private static Box dfs(Node node, Map<Integer, List<Box>> map) {
        if (node == null) {
            return new Box( null, 0, 0);
        }
        Box left = dfs(node.left, map);
        Box right = dfs(node.right, map);
        int descriptor = left.descriptor | right.descriptor;
        descriptor = setBit(descriptor, node.value - 'a');
        Box box  = new Box(node, descriptor, left.size + right.size + 1);
        map.computeIfAbsent(descriptor, k -> new ArrayList<>()).add(box);
        return box;
    }

    public static int setBit( final int flag, final int i ) {
        return flag | (1<<i);
    }
}
