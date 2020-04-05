// HOLY SHIT, NO AUTOCOMPLETE ?

import java.util.*;

public class HexSum {

    public static void main(String[] argv) {
        System.out.println("1000".equals(sum("fed", "13")));
        System.out.println("23".equals(sum("13", "10")));
        System.out.println("0".equals(sum("0", "0")));
        System.out.println("10".equals(sum("10", "0")));
        System.out.println("10".equals(sum("10", "")));

    }

    public static String sum(String hex1, String hex2) {
        int maxSumSize = Math.max(hex1.length(), hex2.length()) + 1;
        char[] result = new char[maxSumSize];
        int vUme = 0;
        for (int i = 0; i < maxSumSize; i++) {
            int part1 = fromHex(hex1, i);
            int part2 = fromHex(hex2, i);
            int partSum = part1 + part2 + vUme;
            vUme = 0;
            if (partSum > 15) {
                vUme = 1;
                partSum = partSum - 16;
            }
            result[i] = toHex(partSum);
        }

        StringBuilder builder = new StringBuilder();
        for (int j = maxSumSize - 1; j >= 0; j--) {
            if (result[j] != '0' || j != maxSumSize - 1) {
                builder.append(result[j]);
            }
        }
        return builder.toString();
    }

    public static char toHex(int sum) {
        if (sum < 10) return (char) ('0' + sum);
        return (char) ('a' + sum - 10);
    }

    public static int fromHex(char c) {
        if (c >= 'a' && c < 'g') return c - 'a' + 10;
        return c - '0';
    }

    public static int fromHex(String hex, int index) {
        int reversedIndex = hex.length() - index - 1;
        if (reversedIndex < hex.length() && reversedIndex >= 0) {
            return fromHex(hex.charAt(reversedIndex));
        }
        return 0;
    }
}
