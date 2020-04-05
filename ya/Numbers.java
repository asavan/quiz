import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by asavan on 23.09.2017.
 */
public class Numbers {

    public static long getSum(long q) {
        long sum = 0;
        while (q > 0) {
            sum += q%10;
            q /=10;
        }
        return sum;
    }
    public static void main(String[] args) {

        ArrayList<Long> result = new ArrayList<>(1000);
        for (long i = 1; i < 200000; ++i) {
            long s = getSum(i);
            if  ((3 * i) % (s*s) != 0) {
                continue;
            }
            long res = (3 * i) / (s*s);
//            if (res > 900000) {
//                continue;
//            }
            result.add(res);
            // System.out.print(res);
            // System.out.print(" ");
            if (res == 2) {
               System.out.print( i+ "\n");
            }
        }
        Collections.sort(result);
        int index = 0;
        for (long i = 1; i < 1000; ++i) {
            if (result.get(index) != i) {
                System.out.println("!!!" + i);
            }
            while (result.get(index) == i) {
                ++index;
            }

        }
        System.out.println(result);
        System.out.print(result.size());
    }
}
