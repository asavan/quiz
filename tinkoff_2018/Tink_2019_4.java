import java.util.Scanner;

public class Tink_2019_4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        char[][] arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            String in = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.charAt(j);
            }
        }

        int y = scanner.nextInt() - 1;
        int x = scanner.nextInt() - 1;
        int sum = space(x, y, arr);
        System.out.println(sum);

    }

    static int space(int x, int y, char[][] arr) {
        if (arr[y][x] == '*') {
            return 0;
        }
        arr[y][x] = '*';
        return 1 +
                space(x + 1, y, arr) +
                space(x - 1, y, arr) +
                space(x, y + 1, arr) +
                space(x, y + 1, arr);
    }
}
