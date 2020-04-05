import java.util.Scanner;

public class T2_Start_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long s = scanner.nextLong();
        long t = scanner.nextLong();
        long res = dist(s, t);
        System.out.println(res);
    }

    private static long dist(long s, long t) {
        long res = t % (2 * s);
        if (res >= s) {
            return 2*s - res;
        }
        return res;
    }
}
