package meta.verticaltree;

import java.util.ArrayList;
import java.util.List;

public class VerticalTree {
    static class Node {
        public Node left, right;
        public int val;
    }
    record Dim(int left, int right){
        public int size() {
            return right - left + 1;
        }
    }

    public static void printTree(Node root) {
        Dim d = traverse(root, new Dim(0, 0));
        List<List<Integer>> result = new ArrayList<>(d.size());
        for (int i = 0; i < d.size(); ++i) {
            result.add(new ArrayList<>());
        }
        traverseFill(result, d, 0, root);
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

    private static void traverseFill(List<List<Integer>> result, Dim d, int curX, Node root) {
        if (root == null) {
            return;
        }
        result.get(curX - d.left).add(root.val);
        traverseFill(result, d, curX - 1, root.left);
        traverseFill(result, d, curX + 1, root.right);
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
