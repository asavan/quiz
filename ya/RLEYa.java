import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by asavan on 09.08.2018.
 */
public class RLEYa {
    @Test
    public void test() {
        assertEquals("", RLE(""));
        assertEquals("a3b", RLE("aaab"));
        assertEquals( "a", RLE("a"));
    }

    private static String RLE(String a) {
        StringBuilder b = new StringBuilder();
        char currentChar = '\0';
        int currentCount = 0;

        for (int i = 0; i < a.length(); ++i) {
            char c = a.charAt(i);
            if (c == currentChar) {
                ++currentCount;
            } else {
                if (currentCount > 1) {
                    b.append(String.valueOf(currentCount));
                }
                b.append(c);
                currentCount = 1;
                currentChar = c;
            }
        }

        if (currentCount > 1) {
            b.append(String.valueOf(currentCount));
        }
        return b.toString();
    }
}

