import java.util.Arrays;

/**
 * Created by asavan on 25.04.2020.
 * https://www.youtube.com/watch?v=dV9REg-GZgk
 * Перечислить все пары двузначных чисел, при умножении дающих четырёхзначное
 * число, состоящее из тех же четырёх (для простоты, различных) чисел. (А.Нестеров)
 */
public class Multiply2020Savvateev {

    static private boolean check(int res, boolean removeDuplicates, boolean onlyOnce, int[] arr) {
        if (res < 1000) {
            return false;
        }
        int[] dest = new int[4];
        System.arraycopy(arr, 0, dest, 0, arr.length);
        Arrays.sort(dest);
        if (removeDuplicates) {
            for (int i = 0; i < dest.length - 1; i++) {
                if (dest[i] == dest[i + 1]) {
                    return false;
                }
            }
        }
        while (res > 0) {
            int digit = res % 10;
            boolean found = false;
            for (int i = 0; i < dest.length; i++) {
                int d = dest[i];
                if (d == digit) {
                    found = true;
                    if (onlyOnce) {
                        dest[i] = -1;
                    }
                    break;
                }
            }
            if (!found) {
                return false;
            }
            res /= 10;
        }
        return true;
    }

    static int mult(int[] arr, int level, boolean removeDuplicates, boolean onlyOnce) {
        if (level == 4) {
            int res = calc(arr);
            if (check(res, removeDuplicates, onlyOnce, arr)) {
                print(arr);
                return 1;
            }
            return 0;
        }
        int counter = 0;
        for (int i = 0; i <= 9; ++i) {
            arr[level] = i;
            counter += mult(arr, level + 1, removeDuplicates, onlyOnce);
        }
        return counter;
    }

    private static void print(int[] arr) {
        System.out.print(Arrays.toString(arr));
        System.out.print(" ");
        System.out.println(calc(arr));
    }

    private static int calc(int[] arr) {
        return (arr[0] * 10 + arr[1]) * (arr[2] * 10 + arr[3]);
    }


    public static void main(String[] args) {
        int[] arr = {-1, -1, -1, -1};
        int counter = mult(arr, 0, false, true);
        System.out.println(counter);
    }
}
