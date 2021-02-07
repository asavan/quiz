package ya.a1;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by asavan on 08.02.2021.
 */
public class CompressRange {
    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 2, 3, 9, 8, 11, 0};
        String res = compress(arr);
        System.out.println(res); // 0-5,8-9,11
    }

    private static String compress(int[] arr) {
        if (arr.length == 0) {
            return "";
        }
        Arrays.sort(arr);
        int begin = arr[0];
        int end = begin;
        StringBuilder b = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            if (cur == end + 1) {
                ++end;
            } else {
                addToString(b, begin, end);
                begin = cur;
                end = begin;
            }
        }
        addToString(b, begin, end);
        return b.toString();
    }

    private static void addToString(StringBuilder b, int begin, int end) {
        if (b.length() > 0) {
            b.append(",");
        }
        b.append(begin);
        if (begin != end) {
            b.append("-");
            b.append(end);
        }
    }

    @Test
    public void test() {
        {
            int[] arr = {1, 4, 5, 2, 3, 9, 8, 11, 0};
            assertEquals("0-5,8-9,11", compress(arr));
        }
        {
            int[] arr = {};
            assertEquals("", compress(arr));
        }
        {
            int[] arr = {2};
            assertEquals("2", compress(arr));
        }
        {
            int[] arr = {2, 3, 4};
            assertEquals("2-4", compress(arr));
        }

    }
}
