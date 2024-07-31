package ya;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        var list = new ArrayList<Node>();
        list.add(new Node(1, 3));
        var list2 = new ArrayList<Node>();
        list2.add(new Node(1, 2));
        list2.add(new Node(10, 1));
        System.out.println(dotProduct(list, list2));
    }

    private static int dotProduct(List<Node> list, List<Node> list2) {
        if (list.isEmpty() || list2.isEmpty()) {
            return 0;
        }
        int ind1 = 0;
        int ind2 = 0;
        int result = 0;
        var node1 = list.get(ind1).clone();
        var node2 = list2.get(ind2).clone();
        while (true) {
            if (node1.count < node2.count) {
                node2.count -= node1.count;
                result += node1.value * node2.value * node1.count;
                ++ind1;
                if (ind1 >= list.size()) {
                    break;
                }
                node1 = list.get(ind1).clone();
            } else if (node1.count > node2.count) {
                node1.count -= node2.count;
                result += node1.value * node2.value * node2.count;
                ++ind2;
                if (ind2 >= list2.size()) {
                    break;
                }
                node2 = list2.get(ind2).clone();
            } else {
                ++ind1;
                ++ind2;
                result += node1.value * node2.value * node1.count;
                if(ind1 >= list.size() || ind2 >= list2.size()) {
                    break;
                }
                node1 = list.get(ind1).clone();
                node2 = list2.get(ind2).clone();
            }

        }
        return result;
    }
}
