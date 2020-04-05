import java.util.*;

public class TT33_Tri {
    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);;
        int n = scanner.nextInt();
        Set<Point> points = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add(new Point(x, y));
        }

        if (hasTriangle(points)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean hasTriangle(Set<Point> points) {
        if (points.size() < 3) {
            return false;
        }
        Iterator<Point> it = points.iterator();
        Point a = it.next();
        Point b = it.next();
        while (it.hasNext()) {
            Point c = it.next();
            if (!isCollinear(a, b, c)) {
                return true;
            }
            a = b;
            b = c;
        }
        return false;
    }

    private static boolean isCollinear(Point a, Point b, Point c) {
        int x1 = b.getX() - a.getX();
        int y1 = b.getY() - a.getY();
        int x2 = c.getX() - b.getX();
        int y2 = c.getY() - b.getY();
        return y2 * x1 == y1 * x2;
    }
}
