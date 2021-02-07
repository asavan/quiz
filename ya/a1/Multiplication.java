package ya.a1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by asavan on 08.02.2021.
 */
public class Multiplication {
    public static void main(String[] args) {
        String big = "1234567890";
        int n = 3;
        String res = multiply(big, n);
        System.out.println(res);
    }

    private static String multiply(String big, int n) {
        if (big.isEmpty()) {
            return "";
        }
        if (n == 0) {
            return "0";
        }
        if (n == 1) {
            return big;
        }
        int vUme = 0;
        StringBuilder b = new StringBuilder();
        for (int i = big.length() - 1; i >= 0; --i) {
            int digit = big.charAt(i) - '0';
            int result = digit * n + vUme;
            if (result >= 10) {
                vUme = result / 10;
                result = result % 10;
            } else {
                vUme = 0;
            }
            b.append((char) ('0' + result));
        }
        if (vUme != 0) {
            b.append((char) ('0' + vUme));
        }
        return b.reverse().toString();
    }

    @Test
    public void test() {
        assertEquals("9", multiply("3", 3));
        assertEquals("27", multiply("3", 9));
        assertEquals("9", multiply("9", 1));
        assertEquals("891", multiply("99", 9));
        assertEquals("3703703670", multiply("1234567890", 3));
        assertEquals("11111111010", multiply("1234567890", 9));
    }
}
