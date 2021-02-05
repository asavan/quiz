package ya.a2;

import static java.util.Arrays.sort;

/**
 * Created by asavan on 05.02.2021.
 */
public class Sum3 {
    public static void main(String[] args) {
        int[] a = {2, 4, 5};
        int[] b = {2, 4, 5};
        int[] c = {2, 4, 5, 6, 7};
        {
            int res = sum3(a, b, c, 14);
            System.out.println(res);
        }
        {
            int res = sum3(a, b, c, 17);
            System.out.println(res);
        }
    }

    private static int sum3(int[] a, int[] b, int[] c, int target) {
        sort(a);
        sort(b);
        int count = 0;
        for (int third : c) {
            count += findSumCount(a, b, target - third);
        }
        return count;
    }

    private static int findSumCount(int[] a, int[] b, int target) {
        int count = 0;
        int i = 0;
        int j = b.length - 1;
        while (i < a.length && j >= 0) {
            int aEl = a[i];
            int bEl = b[j];
            if (aEl + bEl == target) {
                ++count;
                if (i + 1 < a.length && a[i+1] == aEl) {
                    ++i;
                } else {
                    --j;
                }
            } else {
                if (aEl + bEl < target) {
                    ++i;
                } else {
                    --j;
                }
            }
        }
        return count;
    }
}
