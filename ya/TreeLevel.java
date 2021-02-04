package ya;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asavan on 04.02.2021.
 */
public class TreeLevel {
    static class Node {
        int value;
        Node l;
        Node r;
    };
    static class Handler {
        List<Integer> array = new ArrayList<>();
        public void init(Node node) {
            traverse(0, node);
            for (int i = 1; i < array.size(); i++) {
                Integer prev = array.get(i);
                array.set(i, array.get(i-1));
            }
        }
        public void traverse(int level, Node node) {
            if (node == null) return;
            if (array.size() <= level) {
                array.add(node.value);
            } else {
                Integer prev = array.get(level);
                array.set(level, prev + node.value);
            }
            traverse(level + 1, node.l);
            traverse(level + 1, node.r);
        }
        public int sum(int level) {
            if (level < array.size()) {
                return array.get(level);
            }
            return array.get(array.size()-1);
        }
    }

}
