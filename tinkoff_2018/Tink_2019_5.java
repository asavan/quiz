import java.util.Scanner;

public class Tink_2019_5 {

    static class Point {
        int x;
        int y;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Point[] arr = new Point[n];
        for (int i = 0; i < n; i++) {
            Point point = new Point();
            point.x = scanner.nextInt();
            point.y = scanner.nextInt();
            arr[i] = point;
        }

        long s2 = 0;
        long count = 0;
        for (int i = 0; i < n; ++i) {
            s2 += squareX2(arr[i], arr[(i + 1)%n]);
            int tempCount = count(arr[i], arr[(i + 1)%n]);
            count += tempCount;
        }
        count -= n;
        long res = (Math.abs(s2) - count + 2) / 2;
        System.out.println(res);
    }

    private static int count(Point p1, Point p2) {
        return gcd(Math.abs(p1.y - p2.y), Math.abs(p1.x - p2.x)) + 1;
    }

    private static int squareX2(Point p1, Point p2) {
        return p1.x * p2.y - p1.y * p2.x;
    }
}
