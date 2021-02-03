package microsoft;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.youtube.com/watch?v=581GAbMZUOA
 */
public class KClosestPoints {

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int squareDistance(Point other) {
            return square(x-other.x) + square(y-other.y);
        }
        private int square(int x) {
            return x*x;
        }

        public double Distance(Point other) {
            return Math.sqrt(squareDistance(other));
        }

        @Override
        public String toString() {
            return "( " + x + ", " + y + " )";
        }
    }

    public static void main(String[] args) {
        List<Point> input = new ArrayList<>();
        input.add(new Point(1, 1));
        input.add(new Point(2, 2));
        input.add(new Point(-1, 1));
        input.add(new Point(1, -1));
        input.add(new Point(-2, 2));
        input.add(new Point(5, -5));
        input.add(new Point(-8, 6));
        input.add(new Point(0, 20));
        input.add(new Point(0, 0));

        Point vertex = new Point(0, 0);
        List<Point> closest = kClosest(input, vertex, 3);
        for (Point point : closest) {
            System.out.println(point);
        }
    }

    private static List<Point> kClosest(List<Point> input, Point vertex, int k) {
        PriorityQueue<Point> queue = new PriorityQueue<>(k+1, Comparator.comparingInt(o -> -o.squareDistance(vertex)));
        for (Point point : input) {
            queue.add(point);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return new ArrayList<>(queue);
    }

}
