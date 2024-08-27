package ya;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PSS2 {
    public static void main(String[] args) {
        generate(3, System.out::println);
    }

    private static void generate(int n, Consumer<String> callback) {
        StringBuilder b = new StringBuilder();
        int open = 0;
        int closed = 0;
        genInner(n, b, '(', open + 1, closed, callback);
    }

    private static void genInner(int n, StringBuilder b, char c, int open, int closed, Consumer<String> callback) {
        if (open == n && closed == n) {
            b.append(c);
            callback.accept(b.toString());
            b.setLength(b.length() - 1);
            return;
        }
        if (open > n) {
            return;
        }
        if (closed > open) {
            return;
        }
        b.append(c);
        genInner(n, b, '(', open + 1, closed, callback);
        genInner(n, b, ')', open, closed + 1, callback);
        b.setLength(b.length() - 1);
    }

    static class ArrayConsumer implements Consumer<String> {
        public ArrayList<String> arr = new ArrayList<>();
        @Override
        public void accept(String s) {
            arr.add(s);
        }
    }

    @Test
    public void test() {
        {
            var cons = new ArrayConsumer();
            generate(2, cons);
            assertEquals(List.of("(())", "()()"), cons.arr);
        }
        {
            var cons = new ArrayConsumer();
            generate(0, cons);
            assertTrue(cons.arr.isEmpty());
        }
        {
            var cons = new ArrayConsumer();
            generate(3, cons);
            assertEquals(List.of("((()))", "(()())", "(())()", "()(())", "()()()"), cons.arr);
        }
    }
}
