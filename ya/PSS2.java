package ya;

import java.util.function.Consumer;

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
}
