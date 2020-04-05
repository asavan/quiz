import java.util.Scanner;

public class Main5 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] trace = new int[n];
        int realM = m;
        for (int i = 0; i < m; ++i) {
            boolean allZeroes = true;
            for (int j = 0; j < n; ++j) {
                int v = scanner.nextInt();
                if (v != 0) {
                    allZeroes = false;
                    trace[j] = 1;
                }
            }
            if (allZeroes) {
                --realM;
            }
        }
        int calc = 0;
        for (int i = 0; i < n; ++i) {
            calc += trace[i];
        }
        int ans = Math.min(calc, realM);
        System.out.print(ans);
    }

}