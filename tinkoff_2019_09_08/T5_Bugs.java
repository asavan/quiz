import java.util.Scanner;

public class T5_Bugs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        long K = scanner.nextLong();
        int B = scanner.nextInt();
        long M = scanner.nextLong();
        long X = scanner.nextLong();

        long num = days(A, K, B, M, X);
        // long num = days(19, 3, 14, 6, 113);
        System.out.println(num);
    }

    private static long days(int A, long K, int B, long M, long X) {
        long days = 0;
        while (X > 0) {
            ++days;
            if (days % K != 0) {
                X -= A;
            }
            if (days % M != 0) {
                X -= B;
            }
        }
        return days;
    }
}
