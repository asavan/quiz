package ya;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

public class DeletePSS2 {
    public static void main(String[] args) {
        System.out.println(remove("t((ab))e"));
    }

    private static String remove(String s) {
        var arr = s.toCharArray();
        int newLen = removeInner(arr);
        return new String(arr, 0, newLen);
    }

    private static int removeInner(char[] charArray) {
        int curWrite = 0;
        var arr = new ArrayDeque<Integer>();
        int currInd = 0;
        for (char c: charArray) {
            if (c == '(') {
                arr.add(currInd);
                charArray[curWrite++] = c;
            } else if (c == ')') {
                if (arr.isEmpty()) {
                    charArray[curWrite++] = c;
                } else {
                    curWrite = arr.removeLast();
                }
            } else {
                charArray[curWrite++] = c;
            }
            ++currInd;
        }
        return curWrite;
    }

    @Test
    public void test() {
        assertEquals("te", remove("t((ab))e"));
    }
}
