import java.util.Scanner;

public class T1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long l = scanner.nextLong();
        long r = scanner.nextLong();

        long result = 0;
        for (long i = l; i <= r; ++i) {
            result += collatz(i);
        }
        System.out.println(result);
    }

    private static int collatz(long n) {
        if (n == 1) {
            return 0;
        }

        int x = 0;

        do {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
            ++x;
        } while (n >= 2);

        return x;
    }
}
