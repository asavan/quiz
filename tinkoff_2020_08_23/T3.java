import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class T3 {
    public static class Vertex {
        public int x;
        public int y;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int vx1 = scanner.nextInt();
        int vy1 = scanner.nextInt();
        Vertex[] v1 = new Vertex[n];
        for (int i = 0; i < n; i++) {
            Vertex v = new Vertex();
            v.x = scanner.nextInt();
            v.y = scanner.nextInt();
            v1[i] = v;
        }

        int m = scanner.nextInt();
        int vx2 = scanner.nextInt();
        int vy2 = scanner.nextInt();
        Vertex[] v2 = new Vertex[n];
        for (int i = 0; i < m; i++) {
            Vertex v = new Vertex();
            v.x = scanner.nextInt();
            v.y = scanner.nextInt();
            v2[i] = v;
        }

        int vx = vx1 - vx2;
        int vy = vy1 - vy2;

        if (vx == 0 && vy == 0) {
            for (int i = 0; i < n; i++) {
                Vertex v21 = v2[i];
                Vertex v22 = v2[(i + 1) % n];
                for (int j = 0; j < n; j++) {
                    Vertex v11 = v1[j];
                    Vertex v12 = v1[(j + 1) % n];
                    if (intersect(v11, v12, v21, v22)) {
                        System.out.println("Yes");
                        return;
                    }
                }
            }
            System.out.println("No");
            return;
        }

        for (Vertex vertex : v1) {
            for (int i = 0; i < n; i++) {
                Vertex v21 = v2[i];
                Vertex v22 = v2[(i + 1) % n];
                if (isIntersect(vertex, vx, vy, v21, v22)) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }

    private static boolean isIntersect(Vertex v1, int vx, int vy, Vertex v21, Vertex v22) {
        return f(v1, vx, vy, v21) * f(v1, vx, vy, v22) <= 0;
    }

    private static int f(Vertex v1, int vx, int vy, Vertex dest) {
        return (dest.x - v1.x) * vy + (v1.y - dest.y) * vx;
    }

    private static int area (Vertex a, Vertex b, Vertex c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    private static boolean intersect_1 (int a, int b, int c, int d) {
        if (a > b)  {
            int temp = a;
            a = b;
            b = temp;
        }
        if (c > d)  {
            int temp = c;
            c = d;
            d = temp;
        }
        return max(a,c) <= min(b,d);
    }

    private static boolean intersect (Vertex a, Vertex b, Vertex c, Vertex d) {
        return intersect_1 (a.x, b.x, c.x, d.x)
                && intersect_1 (a.y, b.y, c.y, d.y)
                && area(a,b,c) * area(a,b,d) <= 0
                && area(c,d,a) * area(c,d,b) <= 0;
    }
}
