package tinkoff_2020_07_13;

/**
 * Created by asavan on 13.07.2020.
 */
public class Terver200 {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i <= Math.pow(10, 6); ++i) {
            int m = modulo(i, 40, 100);
            if (m == 1) {
                ++count;
            }
        }
        System.out.println(count);
        System.out.println(count* 1000/ Math.pow(10, 6));
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
