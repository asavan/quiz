package ya.a2;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VerticalLine2 {
    static class Point {
        public int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point other)) return false;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static boolean hasVerticalSymLine(Point[] points) {
        if (points.length == 0) {
            return true;
        }

        var map = Arrays.stream(points).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        var min = Arrays.stream(points).mapToInt(p -> p.x).min().getAsInt();
        var max = Arrays.stream(points).mapToInt(p -> p.x).max().getAsInt();

        int mid2 = min + max;
        for (var p: points) {
            var keySim = new Point(mid2 - p.x, p.y);
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
