import java.util.Arrays;

/**
 * Created on 14.08.2020.
 * https://www.youtube.com/watch?v=UaI3WeesCoE
 * https://en.wikipedia.org/wiki/Dutch_national_flag_problem
 */
public class ListPartition {
    public static void main(String[] args) {
        testCase(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 3, 3, 3});
        testCase(new int[]{1, 2, 1, 0, 3, 3, 3, 1, 9, 8, 3, 1, 2, 3, 3, 1, 0, 8, 9});
        testCase(new int[]{4, 5, 3, 2, 1, 0, 3, 7});
        testCase(new int[]{3, 1, 2});
        testCase(new int[]{3, 1});
    }

    private static void testCase(int[] arr) {
        partition(arr, 3);
        print(arr);
    }

    private static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void partition(int[] arr, int k) {
        int s = 0;
        int l = arr.length;
        int mid = 0;
        while (mid < l) {
            if (arr[mid] < k) {
                swap(arr, s, mid);
                ++s;
                ++mid;
            } else if (arr[mid] > k) {
                --l;
                swap(arr, mid, l);
            } else {
                ++mid;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        // optimization
        if (arr[i] <= arr[j]) return;

        // swap
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
