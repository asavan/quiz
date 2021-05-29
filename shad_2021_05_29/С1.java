package shad_2021_05_29;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * Created by asavan on 29.05.2021.
 */
public class С1 {


    public static void main(String[] args) {
        int all = 0;
        int count1 = 0;
        int count2 = 0;
        for (int i = 100000; i <= 999999; ++i ) {
            ++all;
            String str = String.valueOf(i);
            if (!str.contains("1")) {
                ++count1;
            } else if (str.contains("0")) {
                ++count2;
            }
        }
        int count = count1 + count2;
        System.out.println(all);
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(count);
        System.out.println((double) count / all);
        System.out.println(round((double) count / all, 5));
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
