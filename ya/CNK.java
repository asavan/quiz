import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by asavan on 06.01.2019.
 */
public class CNK {

    private static long[] facts = new long[11];

    static long cnk(int n, int k) {
        return fact(n) / fact(n-k) / fact(k);
    }

    static private long fact(int n) {
        return facts[n];
    }

    static int sign(int j) {
        return j%2 == 0  ? 1 : -1;
    }


    public static void main(String[] args) {

        long fac = 1;
        facts[0] = 1;
        for (int i = 1; i <= 10; ++i) {
            System.out.println(facts[i]);
            fac *=i;
            facts[i] = fac;
            System.out.println(facts[i]);
        }

        for (int i = 0; i <= 10; ++i) {
            System.out.print(" " + facts[i]);
        }

        System.out.println(sign(1));

        long sum = 0;
        for (int i = 10; i <= 10; ++i) {
            for (int j = i; j <= 10; ++j) {
                long sum1 = sign(j) * cnk(10, j) * cnk(j, i);
                sum += sum1;
                System.out.println("sum1 " + sum1);
                System.out.println("sum2 " +sum);
            }
                System.out.println("sum3 " +sum + " " + i + " ");
        }
        System.out.println("!!! " + sum);
    }
}
