package meta.merge;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static meta.merge.MergeTwoSorted.*;


public class MergeTwoSortedTest {
    @Test
    public void test() {
        var a = List.of(new Interval(1, 2), new Interval(3, 9));
        var b = List.of(new Interval(4, 6), new Interval(8, 10), new Interval(11, 12));
        var result = List.of(new Interval(1, 2), new Interval(3, 10), new Interval(11, 12));
        assertEquals(result, merge(a, b));
        assertEquals(result, merge(b, a));
    }
}
