package ya.a1;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by asavan on 05.02.2021.
 */
public class Fuzzysearch {
    public static void main(String[] args) {
        boolean res = fuzzysearch("cat", "abcaerrtpiller");
        System.out.println(res);
    }

    @Test
    public void test() {
        assertTrue(fuzzysearch("cat", "abcaerrtpiller"));
        assertTrue(fuzzysearch("car", "cartwheel"));        // true
        assertTrue(fuzzysearch("cwhl", "cartwheel"));       // true
        assertTrue(fuzzysearch("cwheel", "cartwheel"));     // true
        assertTrue(fuzzysearch("cartwheel", "cartwheel"));  // true
        assertFalse(fuzzysearch("cwheeel", "cartwheel"));    // false
        assertFalse(fuzzysearch("lw", "cartwheel"));
        assertTrue(fuzzysearch("", "cartwheel"));
        assertTrue(fuzzysearch("t", "cartwheel"));
    }

    private static boolean fuzzysearch(String search, String text) {
        if (search.isEmpty()) {
            return true;
        }
        int i = 0;
        for (int j = 0; j < text.length(); ++j) {
            char s = search.charAt(i);
            char t = text.charAt(j);
            if (s == t) {
                ++i;
                if (i == search.length()) {
                    return true;
                }
            }
        }
        return false;
    }
}
