package ya.a2;

import org.junit.Test;

import java.util.Arrays;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VerticalLine2 {
    public record Point(int x, int y) {}

    public static boolean hasVerticalSymLine(Point[] points) {
        if (points.length == 0) {
            return true;
        }

        var map = Arrays.stream(points).collect(groupingBy(identity(), counting()));
        var min = Arrays.stream(points).mapToInt(Point::x).min().getAsInt();
        var max = Arrays.stream(points).mapToInt(Point::x).max().getAsInt();

        long mid2 = (long)min + max;
        for (var p: points) {
            var keySim = new Point((int)(mid2 - p.x()), p.y());
            var countSim = map.get(keySim);
            if (!map.get(p).equals(countSim)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        {
            Point[] points = {new Point(0, 0), new Point(0, 0), new Point(1, 1), new Point(2, 2),
                    new Point(3, 1), new Point(4, 0), new Point(4, 0)};
            assertTrue(hasVerticalSymLine(points)); // true
        }
        {
            Point[] points = {new Point(0, 0), new Point(0, 0), new Point(1, 1), new Point(2, 2),
                    new Point(3, 1), new Point(4, 0)};
            assertFalse(hasVerticalSymLine(points)); // false
        }
        {
            Point[] points = {};
            assertTrue(hasVerticalSymLine(points));
        }
        {
            Point[] points = {new Point(0, 0)};
            assertTrue(hasVerticalSymLine(points));
        }
        {
            Point[] points = {new Point(0, 0), new Point(10, 0)};
            assertTrue(hasVerticalSymLine(points));
        }
        {
            Point[] points = {new Point(0, 0), new Point(11, 1)};
            assertFalse(hasVerticalSymLine(points));
        }
        {
            Point[] points = {new Point(0, 0), new Point(1, 0), new Point(3, 0)};
            assertFalse(hasVerticalSymLine(points));
        }
    }
}
