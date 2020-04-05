import java.util.Scanner;

public class TinkSeptember2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        long[][] arr = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextLong();
            }
        }

        System.out.print(m);
        System.out.print(" ");
        System.out.println(n);

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                System.out.print(arr[n - i - 1][j]);
                if (i < n -1) {
                    System.out.print(" ");
                } else {
                    System.out.println();
                }
            }
        }
    }
}