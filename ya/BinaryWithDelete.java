package ya;


import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/

public class BinaryWithDelete {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private final Map<Integer, Integer> ht = new HashMap<>();
    private final Map<Integer, Integer> result = new HashMap<>();
    private int maxVal = -1;
    private int calcHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        var left = calcHeight(root.left);
        var right = calcHeight(root.right);
        var mx = Math.max(left, right) + 1;
        maxVal = Math.max(maxVal, mx);
        ht.put(root.val, mx);
        return mx;
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return ht.getOrDefault(node.val, 0);
    }

    private void calcResult(TreeNode root, int mx, int d) {
        if (root == null) {
            return;
        }
        result.put(root.val, mx);
        calcResult(root.left, Math.max(mx, d+height(root.right)), d+1);
        calcResult(root.right, Math.max(mx, d+height(root.left)), d+1);
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        calcHeight(root);
        calcResult(root, -1, 0);
        var ans = new int[queries.length];
        int i = 0;
        for (var q: queries) {
            ans[i++] =  result.getOrDefault(q, maxVal);
        }
        return ans;
    }
}
