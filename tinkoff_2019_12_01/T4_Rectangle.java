import java.util.Scanner;

public class T4_Rectangle {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean [][] arr = new boolean[1001][1001];
        for (int i = 0; i < 1001; ++i) {
            for (int j = 0; j < 1001; ++j) {
                arr[i][j] = false;
            }
        }

        int n = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            for (int j = 0; j < x2 - x1; j++) {
                for (int k = 0; k < y2 - y1; k++) {
                    arr[x1+j][y1+k] = true;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 1001; ++i) {
            for (int j = 0; j < 1001; ++j) {
                if (arr[i][j]) {
                    ++res;
                }
            }
        }

        System.out.println(res);
    }
}
