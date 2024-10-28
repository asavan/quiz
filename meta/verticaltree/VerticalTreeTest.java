package meta.verticaltree;

import org.junit.Test;

import static meta.verticaltree.VerticalTree.*;

public class VerticalTreeTest {
    @Test
    public void test() {
        var node = new Node(6, new Node(3,
                new Node(5, null,
                        new Node(2, new Node(9, null, null), new Node(7, null, null))), null),
                new Node(4, new Node(1, null, null),
                        new Node(0, new Node(8, new Node(10, null, null), null), null)));
        printTree(node);
    }
}
