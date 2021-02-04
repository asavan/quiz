package ya;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * Created by asavan on 04.02.2021.
 */
public class SubstringAnnagramm {
    public static void main(String[] args) {
        int index = findSubstring("datav", "ata");
        System.out.println(index);
    }

    @Test
    public void test() {
        assertEquals(1, findSubstring("datav", "ata"));
        assertEquals(0, findSubstring("atav", "ata"));
        assertEquals(0, findSubstring("aatav", "ata"));
        assertEquals(-1, findSubstring("aa", "ata"));
        assertEquals(1, findSubstring("tata", "ata"));
        assertEquals(1, findSubstring("baata", "ata"));
        assertEquals(0, findSubstring("taata", "ata"));
    }

    public static int findSubstring(String t, String s) {
        int res = -1;
        if (t.length() < s.length()) {
            return res;
        }
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            Integer prev = freq.get(a);
            if (prev != null) {
                freq.put(a, prev + 1);
            } else {
                freq.put(a, 1);
            }
        }

        int zeroes = 0;
        for (int i = 0; i < t.length(); i++) {
            char in = t.charAt(i);
            zeroes += addChar(freq, in, p -> p - 1);
            if (i >= s.length()) {
                char out = t.charAt(i - s.length());
                zeroes += addChar(freq, out, p -> p + 1);
            }
            if (zeroes == freq.size()) {
                res = i - s.length() + 1;
                break;
            }
        }
        return res;
    }

    private static int addChar(Map<Character, Integer> freq, char a, Function<Integer, Integer> aplicator) {
        int zeroes = 0;
        Integer prev = freq.get(a);
        if (prev != null) {
            Integer newVal = aplicator.apply(prev);
            freq.put(a, newVal);
            if (newVal.equals(0)) {
                ++zeroes;
            } else if (prev.equals(0)) {
                --zeroes;
            }
        }
        return zeroes;
    }
}
