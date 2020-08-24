import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class T2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        long cycles = findCycles(arr, n);
        System.out.println(cycles);
    }

    private static long findCycles(int[] p, int n) {
        boolean[] used = new boolean[n];
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                int j = i;
                int length = 0;
                while (!used[j]) {
                    used[j] = true;
                    j = p[j] - 1;
                    ++length;
                }
                set.add(length);
            }
        }
        long sum = 0;
        for (Integer integer : set) {
            sum += integer;
        }
        return sum;
    }
}
