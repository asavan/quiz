package ya;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DotProduct2 {
    static class Node {
        public int value;
        public int count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    static class Mover {
        private int ind;
        private final List<Node> l;
        private int curValue;
        private int curCount;
        public Mover(List<Node> l) {
            this.l = l;
            ind = 0;
            setValue(ind);
        }
        public int getLen() {
            return l.stream().mapToInt(n -> n.count).sum();
        }
        public int count() {
            return curCount;
        }
        public int value() {
            return curValue;
        }
        private void setValue(int ind) {
            var curr = l.get(ind);
            curCount = curr.count;
            curValue = curr.value;
        }
        boolean next(int diff) {
            curCount -= diff;
            while (curCount == 0) {
                ++ind;
                if (ind >= l.size()) {
                    return false;
                }
                setValue(ind);
            }
            return true;
        }
    }

    int dotProduct(List<Node> l1, List<Node> l2) {
        if (l1.isEmpty() && l2.isEmpty()) {
            return 0;
        }
        if (l1.isEmpty() || l2.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        var m1 = new Mover(l1);
        var m2 = new Mover(l2);
        if (m1.getLen() != m2.getLen()) {
            throw new IllegalArgumentException("");
        }
        int result = 0;
        while (true) {
            int diff = Math.min(m1.count(), m2.count());
            result += m1.value() * m2.value() * diff;
            if (!m1.next(diff) || !m2.next(diff)) {
                break;
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
