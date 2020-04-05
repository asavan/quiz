import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
// [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 25, 26, 27, 17, 18, 24, 28, 30, 29]

/**
 * Created by asavan on 06.01.2019.
 */
public class Permutation {

    private static final int SIZE = 30;

    static boolean next_permutation(int[] p) {
        for (int a = p.length - 2; a >= 0; --a)
            if (p[a] < p[a + 1])
                for (int b = p.length - 1; ; --b)
                    if (p[b] > p[a]) {
                        int t = p[a];
                        p[a] = p[b];
                        p[b] = t;
                        for (++a, b = p.length - 1; a < b; ++a, --b) {
                            t = p[a];
                            p[a] = p[b];
                            p[b] = t;
                        }
                        return true;
                    }
        return false;
    }

    public static class Permutations {
        /**
         * Rearrange the list into the next lexicographically greater permutation if exists.
         *
         * @return true if such a permutation exists, otherwise false.
         */
        public static <T extends Comparable> boolean nextPermutation(List<T> list) {
            return nextPermutation(list, (a, b) -> a.compareTo(b));
        }

        /**
         * Rearrange the list into the next lexicographically greater permutation if exists.
         *
         * @return true if such a permutation exists, otherwise false.
         */
        public static <T> boolean nextPermutation(List<T> list, Comparator<T> comparator) {
            // Find the biggest i which meets list[i] < list[i + 1]
            int i = list.size() - 2;
            for (; i >= 0; i--) {
                T next = list.get(i + 1);
                T tmp = list.get(i);
                if (comparator.compare(tmp, next) < 0) {
                    break;
                }
            }
            if (i < 0) {
                return false;
            }
            T pivot = list.get(i);

            // Find the biggest j where list[j - 1] > list[i].
            int j = i + 2;
            for (; j < list.size(); j++) {
                if (comparator.compare(list.get(j), pivot) <= 0) break;
            }

            Collections.swap(list, i, j - 1);
            Collections.reverse(list.subList(i + 1, list.size()));

            return true;
        }
    }

    private static int[] M = {2, 3, 5};

    static void print(Integer[] p) {
        for (int i = 0; i < p.length; ++i) {
            System.out.print(p[i] + " ");
        }
        System.out.println();
    }

    static void print(int[] p) {
        System.out.println(Arrays.toString(p));
    }


    // modifies c to next permutation or returns null if such permutation does not exist
    private static Integer[] nextPermutation(final Integer[] c) {
        // 1. finds the largest k, that c[k] < c[k+1]
        int first = getFirst(c);
        if (first == -1) return null; // no greater permutation
        // 2. find last index toSwap, that c[k] < c[toSwap]
        int toSwap = c.length - 1;
        while (c[first].compareTo(c[toSwap]) >= 0)
            --toSwap;
        // 3. swap elements with indexes first and last
        swap(c, first++, toSwap);
        // 4. reverse sequence from k+1 to n (inclusive)
        toSwap = c.length - 1;
        while (first < toSwap)
            swap(c, first++, toSwap--);
        return c;
    }

    // finds the largest k, that c[k] < c[k+1]
    // if no such k exists (there is not greater permutation), return -1
    private static int getFirst(final Comparable[] c) {
        for (int i = c.length - 2; i >= 0; --i)
            if (c[i].compareTo(c[i + 1]) < 0)
                return i;
        return -1;
    }

    // swaps two elements (with indexes i and j) in array
    private static void swap(final Comparable[] c, final int i, final int j) {
        final Comparable tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }

    private static void printPermutations(int[] c) {
        // System.out.println( Arrays.toString( c ) );
        int count = 0;
        long all = 0;
        if (check(c)) {
            ++count;
            ++all;
            System.out.println("Good " + Arrays.toString(c));
        }
        while (next_permutation(c)) {
            ++all;
            if (check(c)) {
                ++count;
                System.out.println("Good " + Arrays.toString(c));
            }
            if (all % 200000000 == 0) {
                System.out.println(all + " " + Arrays.toString(c));
            }
        }
        System.out.println("!!!!!!! " + count);
    }

    private static void printPermutations(Integer[] c) {
        // System.out.println( Arrays.toString( c ) );
        int count = 0;
        long all = 0;
        if (check(c)) {
            ++count;
            ++all;
            System.out.println("Good " + Arrays.toString(c));
        }
        while (Permutations.nextPermutation(Arrays.asList(c))) {
            ++all;
            if (check(c)) {
                ++count;
                System.out.println("Good " + Arrays.toString(c));
            }
            if (all % 50000000 == 0) {
                System.out.println(all + " " + Arrays.toString(c));
            }
        }
        System.out.println("!!!!!!! " + count);
    }

    private static boolean check(Integer[] c) {
        for (int i = 0; i < M.length; ++i) {
            int m = M[i];
            for (int j = 0; j < c.length - m; ++j) {
                if ((c[m + j] - c[j]) % m != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static void shift(int[] c) {
        int first = c[0];
        for (int i = 0; i < c.length - 1; ++i) {
            c[i] = c[i + 1];
        }
        c[c.length - 1] = first;
    }

    private static void reverse(int[] validData) {
        for (int i = 0; i < validData.length / 2; i++) {
            int temp = validData[i];
            validData[i] = validData[validData.length - i - 1];
            validData[validData.length - i - 1] = temp;
        }
    }

    private static boolean check(int[] c) {
        for (int i = 0; i < M.length; ++i) {
            int m = M[i];
            for (int j = 0; j < c.length - m; ++j) {
                if ((c[m + j] - c[j]) % m != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static void checkShifts(int[] c) {
        int count = 0;
        long all = 0;
        if (check(c)) {
            ++count;
            ++all;
            // System.out.println("Good " + Arrays.toString(c));
        }

        for (int i = 0; i < c.length - 1; ++i) {
            shift(c);
            if (check(c)) {
                ++count;
                ++all;
                // System.out.println("Good " + Arrays.toString(c));
            }
        }
        System.out.println(all + " !!!!!!! " + count);
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; ++i) {
            if (i * (i - 1) / 2 > 700) {
                System.out.println(i);
                break;
            }
        }
        int[] p = new int[SIZE];
        for (int i = 0; i < SIZE; ++i) {
            p[i] = i + 1;
        }
        print(p);
        checkShifts(p);
        reverse(p);
        checkShifts(p);
        // printPermutations(p);
        // int[] an = {1, 2, 3, 4, 5, 6};
        // int[] an = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // int[] an = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 1, 2};
        // int[] an = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, 2};
        // int[] an = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 20, 18, 30, 17, 22, 24, 19, 29, 23, 25, 27, 28, 26, 21};
        int [] an = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 1,  12, 13, 14, 15, 16, 17, 18, 19, 20, 11, 22, 23, 24, 25, 26, 27, 28, 29, 30, 21};
//        List<Integer> list = Arrays.asList(an);
//        // Collections.reverse(list);
//        print(an);
        System.out.println(check(an));
        // printPermutations(an);
//        Integer[] next = nextPermutation(p);
//
//        print(p);
//        print(next);
    }
}
