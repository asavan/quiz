package ya.a1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by asavan on 07.02.2021.
 */
public class RemoveBrackets {
    public static void main(String[] args) {
        String input = "Я работаю в яндексе :-)))";
        String res = removeBrakets(input);
        System.out.println(res);
    }

    @Test
    public void test() {
        assertEquals("Я работаю в Яндексе ", removeBrakets("Я работаю в Яндексе :-)))"));
        assertEquals("везет  а я туда собеседование завалил", removeBrakets("везет :-) а я туда собеседование завалил:-(("));
        assertEquals("лол:)", removeBrakets("лол:)"));
        assertEquals("Aaaaa!!!!! (())", removeBrakets("Aaaaa!!!!! :-))(())"));
    }

    enum State {
        DEFAULT,
        COLON,
        TIRE,
        OPEN,
        CLOSED;
    }

    private static String removeBrakets(String input) {
        StringBuilder b = new StringBuilder();
        State state = State.DEFAULT;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (state) {
                case DEFAULT -> {
                    if (c == ':') {
                        state = State.COLON;
                    } else {
                        b.append(c);
                    }
                }
                case COLON -> {
                    if (c == '-') {
                        state = State.TIRE;
                    } else {
                        b.append(':');
                        state = State.DEFAULT;
                        --i;
                    }
                }
                case TIRE -> {
                    if (c == ')') {
                        state = State.CLOSED;
                    } else if (c == '(') {
                        state = State.OPEN;
                    } else {
                        b.append(":-");
                        --i;
                    }
                }
                case OPEN -> {
                    if (c != '(') {
                        state = State.DEFAULT;
                        --i;
                    }
                }
                case CLOSED -> {
                    if (c != ')') {
                        state = State.DEFAULT;
                        --i;
                    }
                }
            }
        }
        return b.toString();
    }
}
