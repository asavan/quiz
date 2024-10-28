package meta.verticaltree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class VerticalTree {
    public static class Node {
        public Node(int v, Node l, Node r) {
            val = v;
            left = l;
            right = r;
        }
        public Node left, right;
        public int val;
    }
    record Dim(int left, int right) {
        public int size() {
            return right - left + 1;
        }
    }

    private record Item(Node n, int ind) {}
    public static void printTree(Node root) {
        Dim d = traverse(root, new Dim(0, 0));
        List<List<Integer>> result = new ArrayList<>(d.size());
        for (int i = 0; i < d.size(); ++i) {
            result.add(new ArrayList<>());
        }
        traverseFill(result, d, root);
        printResult(result);
    }

    private static void printResult(List<List<Integer>> result) {
        for (List<Integer> list: result) {
            for (Integer item : list) {
                System.out.print(item);
                System.out.print(" ");
            }
        }
    }

    private static void traverseFill(List<List<Integer>> result, Dim d, Node root) {
        if (root == null) {
            return;
        }
        Queue<Item> q = new ArrayDeque<>();
        q.add(new Item(root, - d.left));

        while (!q.isEmpty()) {
            Item item = q.poll();
            if (item.n == null) {
                continue;
            }
            result.get(item.ind).add(item.n.val);
            q.add(new Item(item.n.left, item.ind - 1));
            q.add(new Item(item.n.right, item.ind + 1));
        }
    }

    private static Dim traverse(Node root, Dim cur) {
        if (root == null) {
            return cur;
        }
        Dim l = traverse(root.left, new Dim(cur.left - 1, cur.right));
        Dim r = traverse(root.right, new Dim(cur.left, cur.right + 1));
        return new Dim(Math.min(l.left, r.left), Math.max(l.right, r.right));
    }
}
