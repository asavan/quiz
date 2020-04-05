import java.util.Scanner;

public class T4_Start_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        long num = waysNum(x, y);
        System.out.println(num);
    }

    private static long waysNum(int x, int y) {
        long [][] field = new long[8][8];
        for (int j = 0; j < 8; ++j) {
            field[0][j] = 1;
        }
        for (int i = 1; i < 9 - y; ++i) {
            for (int j = 0; j < 8; ++j) {
                long a = 0;
                if (j > 0) {
                    a = field[i-1][j-1];
                }
                long b = 0;
                if (j < 7) {
                    b = field[i-1][j+1];
                }
                field[i][j] = a + b;
            }
        }
        return field[8-y][x-1];
    }
}
