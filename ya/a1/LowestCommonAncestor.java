package ya.a1;

/**
 * Created by asavan on 07.02.2021.
 */
public class LowestCommonAncestor {
    static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value, Node left, Node right, Node parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}' + super.toString();
        }
    }

    public static void main(String[] args) {
        Node left = new Node(2, null, null, null);
        Node right = new Node(2, null, null, null);
        Node root = new Node(1, left , right, null);
        left.parent = root;
        right.parent = root;

        Node res = lca(left, right);
        System.out.println(res);
    }

    private static Node lca(Node left, Node right) {
        boolean leftDone = false;
        boolean rightDone = false;
        Node curLeft = left;
        Node curRight = right;
        while (curLeft != curRight) {
            if (curLeft == null) {
                if (leftDone) {
                    // ERROR no common
                    return null;
                }
                curLeft = right;
                leftDone = true;
            } else {
                curLeft = curLeft.parent;
            }
            if (curRight == null) {
                if (rightDone) {
                    // ERROR no common
                    return null;
                }
                curRight = left;
                rightDone = true;
            } else {
                curRight = curRight.parent;
            }
        }
        return curLeft;
    }
}
