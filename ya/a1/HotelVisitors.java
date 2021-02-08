package ya.a1;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by asavan on 08.02.2021.
 */
public class HotelVisitors {
    static class Days {
        public int begin;
        public int end;

        public Days(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        List<Days> visitors = new ArrayList<>();
        int res = maxVisitors(visitors);
        System.out.println(res);
    }

    private static int maxVisitors(List<Days> visitors) {
        if (visitors.isEmpty()) {
            return 0;
        }
        Map<Integer, Integer> dayToVisitors = new TreeMap<>();
        int max = 0;
        int summ = 0;
        for (Days visitor : visitors) {
            dayToVisitors.merge(visitor.begin, 1, Integer::sum);
            dayToVisitors.merge(visitor.end, -1, Integer::sum);
        }
        for (Integer value : dayToVisitors.values()) {
            summ += value;
            max = Math.max(max, summ);
        }
        return max;
    }

    @Test
    public void test() {
        assertEquals(0, maxVisitors(Collections.emptyList()));
        assertEquals(1, maxVisitors(Arrays.asList(new Days(1, 2))));
        assertEquals(1, maxVisitors(Arrays.asList(new Days(1, 2), new Days(2, 3))));
        assertEquals(2, maxVisitors(Arrays.asList(new Days(1, 5), new Days(0, 1), new Days(4, 5))));
    }
}
