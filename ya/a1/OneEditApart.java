package ya.a1;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by asavan on 06.02.2021.
 */
public class OneEditApart {
    public static void main(String[] args) {
        boolean res = oneEditApart("cat", "can");
        System.out.println(res);
    }

    @Test
    public void test() {
        assertFalse(oneEditApart("cat", "dog"));
        assertTrue(oneEditApart("cat", "cats"));
        assertTrue(oneEditApart("cat", "cut"));
        assertTrue(oneEditApart("cat", "cast"));
        assertTrue(oneEditApart("cat", "at"));
        assertFalse(oneEditApart("cat", "acts"));
        assertTrue(oneEditApart("", "a"));
    }

    private static boolean oneEditApart(String first, String second) {
        String small = first.length() <= second.length() ? first : second;
        String big = first.length() > second.length() ? first : second;
        if (big.length() - small.length() > 1) {
            return false;
        }
        if (big.length() == small.length()) {
            int diff = 0;
            for (int i = 0; i < small.length(); i++) {
                char s = small.charAt(i);
                char b = big.charAt(i);
                if (s != b) {
                    ++diff;
                }
                if (diff > 1) {
                    return false;
                }
            }
            return true;
        }
        int diff = 0;
        for (int i = 0; i < small.length(); i++) {
            char s = small.charAt(i);
            char b = big.charAt(i+diff);
            if (s != b) {
                ++diff;
            }
            if (diff > 1) {
                return false;
            }
        }
        return true;
    }
}
