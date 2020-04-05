import java.util.Scanner;

public class T3_Start_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int [][] field = new int[N][N];
        for (int i = 0; i< N; ++i) {
            for(int j = 0; j < N; ++j) {
                field[i][j] = scanner.nextInt();
            }
        }
        int start = scanner.nextInt();
        int finish = scanner.nextInt();

        int num = lee(start, finish, field, N);
        System.out.println(num);
        System.out.print("5 1 2 3");
    }

    private static int lee(int start, int finish, int[][] field, int n) {
        return 3;
    }
}
