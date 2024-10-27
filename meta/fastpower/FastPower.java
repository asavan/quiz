package meta.fastpower;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FastPower {
    public static int recPower(int a, int pow) {
        if (pow == 1) {
            return a;
        }
        if (isOdd(pow)) {
            return a* recPower(a, pow-1);
        }
        int sqr = recPower(a, pow/2);
        return sqr * sqr;
    }
    public static int recPower2(int a, int pow) {
        if (pow == 1) {
            return a;
        }
        if (isOdd(pow)) {
            return a* recPower2(a, pow-1);
        }
        return recPower2(a*a, pow/2);
    }
    public static boolean isOdd(int a) {
        int half = a/2;
        return half * 2 != a;
    }

    public static int loopPower(int a, int pow) {
        int y = 1;
        while (pow > 1) {
            if (isOdd(pow)) {
                y *= a;
                pow -= 1;
            }
            a = a * a;
            pow /= 2;
        }
        return y * a;
    }

    @Test
    public void test() {
        assertEquals(8, recPower(2, 3));
        assertEquals(8, recPower2(2, 3));
        assertEquals(8, loopPower(2, 3));
        for (int i = 1; i < 7; ++i) {
            int a = 3;
            assertEquals(recPower(a, i), recPower2(a, i));
            assertEquals(recPower(a, i), loopPower(a, i));
        }
    }
}
