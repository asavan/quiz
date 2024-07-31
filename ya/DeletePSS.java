package ya;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeletePSS {
    public static void main(String[] args) {
        System.out.println(remove("ab()c"));
    }

    private static String remove(String s) {
        var arr = s.toCharArray();
        int newLen = removeInner(arr);
        return new String(arr, 0, newLen);
    }

    private static int removeInner(char[] charArray) {
        int curWrite = 0;
        int openLen = 0;
        for (var c: charArray) {
            if (c == '(') {
                ++openLen;
            }
            else if (c == ')') {
                if (openLen > 0) {
                    --openLen;
                } else {
                    charArray[curWrite++] = ')';
                }
            } else {
                while (openLen > 0) {
                    --openLen;
                    charArray[curWrite++] = '(';
                }
                charArray[curWrite++] = c;
            }
        }
        while (openLen > 0) {
            --openLen;
            charArray[curWrite++] = '(';
        }
        return curWrite;
    }

    @Test
    public void test() {
        assertEquals("abc", remove("ab()c"));
        assertEquals("ab(a)", remove("ab(()a)"));
        assertEquals("aba)", remove("aba)"));
        assertEquals("", remove("(((())))"));
        assertEquals("", remove("(()())"));
        assertEquals("(((", remove("(()(("));
        assertEquals("(.)_(.)", remove("(.)_(.)"));
    }
}
