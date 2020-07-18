package tinkoff_2020_07_18;

/**
 * Created by asavan on 13.07.2020.
 */
public class Numbers300 {
    public static void main(String[] args) {
        int r2 = modulo(2, 2020, 900);
        int rres = 0;
        for (int i = 1; i<=2019; ++i) {
            int res = modulo(2, i, 9);
            rres += res;
            rres %= 9;
        }
        rres *= 100;
        System.out.println(rres + r2);
    }

    public static int modulo(int a, int b, int n) {
        long x = 1, y = a;
        while (b > 0) {
            if (b % 2 == 1) {
                x = (x * y) % n; // multiplying with base
            }
            y = (y * y) % n; // squaring the base
            b /= 2;
        }
        return (int) x % n;
    }
}
