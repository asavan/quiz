package ya.a1;

import java.util.Arrays;

/**
 * Created by asavan on 07.02.2021.
 */
public class RemoveZeroes {
    public static void main(String[] args) {
        int [] arr = {1, 0, 2, 3, 0, 0, 4, 0, 0, 5};
        int res = removeZeroes(arr);
        System.out.println(res);
        System.out.println(Arrays.toString(arr));
    }

    public static int removeZeroes(int[] arr) {
        int firstZero = 0;
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i];
            if (digit != 0) {
                if (firstZero != i) {
                    arr[firstZero] = digit;
                    arr[i] = 0;
                }
                ++firstZero;
            }
        }
        return firstZero;
    }
}
