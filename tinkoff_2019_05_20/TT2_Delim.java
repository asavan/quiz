import java.util.Scanner;

public class TT2_Delim {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long x = scanner.nextLong();

        System.out.println(getMinDel(x));
    }

    private static long getMinDel(long x) {
        if (x%2 == 0) {
            return 2;
        }
        if (x %3 == 0) {
            return 3;
        }
        for (long k = 1; ; ++k) {
            long del = 6*k -1;
            if (x % del == 0) {
                return del;
            }
            del = 6*k +1;
            if (x % del == 0) {
                return del;
            }
            if (del * del >= x) {
                return x;
            }
        }
    }
}
