package ya.a1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by asavan on 07.02.2021.
 */
public class MaxOnes {
    public static void main(String[] args) {
        int[] input = {1, 1, 1, 0, 1, 1, 0, 1};
        int res = maxOnes(input);
        System.out.println(res);
    }

    enum State {
        ONES,
        ONES_AFTER_ONE_ZERO,
        ONE_ZERO,
        ZEROES
    }

    public static int maxOnes(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int maxLen = 0;
        int currLen = 0;
        int prevLen = 0;
        State state = State.ZEROES;
        boolean hasZero = false;
        for (int i : arr) {
            switch (state) {
                case ZEROES -> {
                    if (i == 1) {
                        currLen = 1;
                        maxLen = Math.max(currLen, maxLen);
                        state = State.ONES;
                    } else {
                        hasZero = true;
                    }
                }
                case ONES -> {
                    if (i == 1) {
                        ++currLen;
                        maxLen = Math.max(currLen, maxLen);
                    } else {
                        state = State.ONE_ZERO;
                        hasZero = true;
                    }
                }
                case ONE_ZERO -> {
                    if (i == 0) {
                        state = State.ZEROES;
                    } else {
                        state = State.ONES_AFTER_ONE_ZERO;
                        prevLen = currLen;
                        currLen = 1;
                        maxLen = Math.max(currLen + prevLen, maxLen);
                    }
                }
                case ONES_AFTER_ONE_ZERO -> {
                    if (i == 1) {
                        ++currLen;
                        maxLen = Math.max(currLen + prevLen, maxLen);
                    } else {
                        state = State.ONE_ZERO;
                    }
                }
            }
        }
        return (hasZero || maxLen == 0) ? maxLen : maxLen - 1;
    }

    @Test
    public void test() {
        assertEquals(5, maxOnes(new int[]{1, 1, 1, 0, 1, 1, 0, 1}));
        assertEquals(0, maxOnes(new int[]{0, 0}));
        assertEquals(1, maxOnes(new int[]{1, 0}));
        assertEquals(0, maxOnes(new int[]{1}));
        assertEquals(1, maxOnes(new int[]{0, 1}));
        assertEquals(1, maxOnes(new int[]{1, 1}));
        assertEquals(5, maxOnes(new int[]{1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1}));
        assertEquals(6, maxOnes(new int[]{1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1}));
    }
}
