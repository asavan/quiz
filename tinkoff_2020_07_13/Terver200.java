package tinkoff_2020_07_13;

/**
 * Created by asavan on 13.07.2020.
 */
public class Terver200 {
    public static void main(String[] args) {
        int count = 0;
        int i;
        for (i = 0; i < Math.pow(10, 6); ++i) {
            int m = modulo(i + 1, 40, 100);
            if (m == 1) {
                ++count;
            }
        }
        System.out.println(count * 100. / i);
    }

    private static int modulo(int a, int pow, int mod) {
        long x = 1, y = a;
        while (pow > 0) {
            if (pow % 2 == 1) {
                x = (x * y) % mod; // multiplying with base
            }
            y = (y * y) % mod; // squaring the base
            pow /= 2;
        }
        return (int) x % mod;
    }
}
