package ya;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asavan on 04.02.2021.
 */
public class SubstringAnnagramm {
    public static void main(String[] args) {
        int index = findSubstring("tata", "ata");
        System.out.println(index);
    }

    public static int findSubstring(String t, String s) {
        int res = -1;
        Map<Character, Integer> freq = new HashMap<>();
        if (t.length() < s.length()) {
            return res;
        }
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
        for (int i = 0; i < s.length(); i++) {
            char a = t.charAt(i);
            zeroes += addChar(freq, a);
        }
        if (zeroes == freq.size()) {
            res = 0;
            return res;
        }
        for (int i = s.length(); i < t.length(); i++) {
            char out = t.charAt(i - s.length());
            char in = t.charAt(i);
            zeroes += removeChar(freq, zeroes, out);
            zeroes += addChar(freq, in);
            if (zeroes == freq.size()) {
                res = i - s.length() + 1;
                break;
            }
        }
        return res;
    }

    private static int removeChar(Map<Character, Integer> freq, int zeroes, char out) {
        Integer prev = freq.get(out);
        if (prev != null) {
            freq.put(out, prev + 1);
            if (prev + 1 == 0) {
                ++zeroes;
            } else if (prev == 0) {
                --zeroes;
            }
        }
        return zeroes;
    }

    private static int addChar(Map<Character, Integer> freq, char a) {
        int zeroes = 0;
        Integer prev = freq.get(a);
        if (prev != null) {
            freq.put(a, prev - 1);
            if (prev == 1) {
                ++zeroes;
            } else if (prev == 0) {
                --zeroes;
            }
        }
        return zeroes;
    }
}
