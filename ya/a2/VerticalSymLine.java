package ya.a2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by asavan on 05.02.2021.
 */
public class VerticalSymLine {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        {
            Point[] points = {new Point(-1, 1), new Point(1, 1)};
            boolean res = hasVerticalSymLine(points);
            System.out.println(res);
        }
        {
            Point[] points = {new Point(-1, 1), new Point(1, 1), new Point(0, 0)};
            boolean res = hasVerticalSymLine(points);
            System.out.println(res);
        }
    }

    private static boolean hasVerticalSymLine(Point[] points) {
        Map<Point, Integer> allPoints = new HashMap<>();
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (Point point : points) {
            allPoints.merge(new Point(point.x * 2, point.y), 1, Integer::sum);
            minX = Math.min(minX, point.x * 2);
            maxX = Math.max(maxX, point.x * 2);
        }
        int mid = (maxX + minX)/2;
        for (Point point : points) {
            if (point.x*2 == mid) {
                continue;
            }
            Point key = new Point(2 * mid - point.x*2, point.y);
            Integer symPoint = allPoints.get(key);
            if (symPoint == null) {
                return false;
            } else {
                allPoints.put(key, symPoint - 1);
            }
        }
        return true;
    }
}
