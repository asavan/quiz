package tinkoff_2020_07_13;

/**
 * Created by asavan on 13.07.2020.
 */
public class Numbers300 {
    public static void main(String[] args) {
        int r1 = modulo(4, 100, 10000);
        int r2 = modulo(2, 100, 10000);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println((r1-r2)/2);
    }

    public static int modulo(int a, int b, int n){
        long x=1, y=a;
        while (b > 0) {
            if (b%2 == 1) {
                x = (x*y) % n; // multiplying with base
            }
            y = (y*y) % n; // squaring the base
            b /= 2;
        }
        return (int)x % n;
    }
}
