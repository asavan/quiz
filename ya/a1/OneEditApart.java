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
        final String small;
        final String big;
        if (first.length() <= second.length()) {
            small = first;
            big = second;
        } else {
            small = second;
            big = first;
        }
        int lenDiff = big.length() - small.length();
        if (lenDiff > 1) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < small.length(); i++) {
            char s = small.charAt(i);
            char b = big.charAt(i + Math.min(diff, lenDiff));
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
