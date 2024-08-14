package ya;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeletePSS {
    public static void main(String[] args) {
        System.out.println(remove("ab()c"));
    }

    private static String remove(String s) {
        var arr = s.toCharArray();
        int newLen = removeInner3(arr);
        return new String(arr, 0, newLen);
    }

    private static int removeInner2(char[] charArray) {
        int writeInd = 0;
        int openLen = 0;
        for (var c: charArray) {
            if (c == '(') {
                ++openLen;
                charArray[writeInd++] = c;
            } else if (c == ')') {
                if (openLen > 0) {
                    --openLen;
                    --writeInd;
                } else {
                    charArray[writeInd++] = c;
                }
            } else {
                openLen = 0;
                charArray[writeInd++] = c;
            }
        }
        return writeInd;
    }

    private static int removeInner3(char[] charArray) {
        int writeInd = 0;
        int openLen = 0;
        for (var c: charArray) {
            charArray[writeInd++] = c;
            openLen = switch (c) {
                case '(' -> openLen + 1;
                case ')' -> {
                    if (openLen > 0) {
                        writeInd -= 2;
                        yield openLen - 1;
                    } else {
                        yield 0;
                    }
                }
                default -> 0;
            };
        }
        return writeInd;
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
