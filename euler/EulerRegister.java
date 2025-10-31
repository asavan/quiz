package euler;

import java.math.BigInteger;

public class EulerRegister {
    static BigInteger squareSum(int end) {
        var sum = BigInteger.ZERO;
        for (var i = 0; i <= end; ++i) {
            if (i % 2 != 0) {
                var mult = BigInteger.valueOf(i);
                sum = sum.add(mult.multiply(mult));
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(squareSum(5)); // 35
        System.out.println(squareSum(752000)); // 70876501333208000
    }
}
