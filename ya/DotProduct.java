package ya;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DotProduct {
    static class Node {
        public int value;
        public int count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }
        public Node clone() {
            return new Node(value, count);
        }
    }

    private static int len(List<Node> list) {
        int result = 0;
        for (var n: list) {
            result += n.count;
        }
        return result;
    }

    public static void main(String[] args) {
        var list = new ArrayList<Node>();
        list.add(new Node(1, 3));
        var list2 = new ArrayList<Node>();
        list2.add(new Node(1, 2));
        list2.add(new Node(10, 1));
        System.out.println(dotProduct(list, list2));
    }

    private static int dotProduct(List<Node> list, List<Node> list2) {
        if (len(list) != len(list2)) {
            throw new IllegalArgumentException("Bad lists");
        }

        if (list.isEmpty() || list2.isEmpty()) {
            return 0;
        }
        int ind1 = 0;
        int ind2 = 0;
        int result = 0;
        int rest = len(list);
        var node1 = list.get(ind1).clone();
        var node2 = list2.get(ind2).clone();
        while (rest > 0) {
            int diff = Math.min(node1.count, node2.count);
            result += node1.value * node2.value * diff;
            node1.count -= diff;
            node2.count -= diff;
            rest -= diff;
            if (node1.count == 0) {
                ++ind1;
                if (ind1 >= list.size()) {
                    break;
                }
                node1 = list.get(ind1).clone();
            }
            if (node2.count == 0) {
                ++ind2;
                if (ind2 >= list2.size()) {
                    break;
                }
                node2 = list2.get(ind2).clone();
            }
        }
        return result;
    }

    @Test
    public void test() {
        var list = new ArrayList<Node>();
        list.add(new Node(1, 3));
        var list2 = new ArrayList<Node>();
        list2.add(new Node(1, 2));
        list2.add(new Node(10, 1));

        assertEquals(12, dotProduct(list, list2));
    }
}
