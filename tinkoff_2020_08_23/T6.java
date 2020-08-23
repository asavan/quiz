import java.util.Scanner;

public class T6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(tincoin(n));
    }

    private static int tincoin(long n) {
        if (n == 2) {
            return 1;
        }
        if (n % 2 == 0) {
            return 2;
        }
        if (isPrime(n)) {
            return 1;
        }
        if (isPrime(n - 2)) {
            return 2;
        }
        return 3;
    }

    private static boolean isPrime(long n) {
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
