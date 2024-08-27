package ya;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MinSquares {
    static class Helper {
        public int minCount;
        public int prevInd;
        public int value;
        public int ind;
    }

    List<Integer> minSquares(int n) {
        var dp = new ArrayList<Helper>(Collections.nCopies(n + 1, null));
        minInner(dp, n);
        return calcResult(dp, n);
    }

    private List<Integer> calcResult(List<Helper> dp, int n) {
        var result = new ArrayList<Integer>();
        var last = dp.get(n);
        while(last.minCount != 1) {
            result.add(last.value);
            last = dp.get(last.prevInd);
        }
        result.add(last.value);
        return result;
    }

    Helper minInner(List<Helper> dp, int n) {
        if (dp.get(n) != null) {
            return dp.get(n);
        }
        int intSqrt = (int) Math.sqrt(n);
        if (intSqrt * intSqrt == n) {
            var helper = new Helper();
            helper.minCount = 1;
            helper.prevInd = -1;
            helper.value = intSqrt;
            helper.ind = n;
            dp.set(n, helper);
            return helper;
        }
        Helper mH = new Helper();
        mH.minCount = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 1; i <= intSqrt; ++i) {
            var h = minInner(dp, n - i*i);
            if (mH.minCount > h.minCount) {
                mH = h;
                minIndex = i;
            }
        }
        var resH = new Helper();
        resH.minCount = mH.minCount + 1;
        resH.prevInd = mH.ind;
        resH.value = minIndex;
        resH.ind = n;
        dp.set(n, resH);
        return resH;
    }

    @Test
    public void test() {
        assertEquals(List.of(3), minSquares(9));
        assertEquals(List.of(3, 3), minSquares(18));
        assertEquals(List.of(1, 3, 3), minSquares(19));
        assertEquals(List.of(1, 1, 1, 2), minSquares(7));
    }
}
