import java.util.Locale;
import java.util.Scanner;

public class T6_Polygon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);;
        int n = scanner.nextInt();
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextDouble();
            y[i] = scanner.nextDouble();
        }
        double px = scanner.nextDouble();
        double py = scanner.nextDouble();
        if (pnpoly(n, x, y, px, py)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean pnpoly(int npol, double[] xp, double[] yp, double x, double y)
    {
        boolean c = false;
        for (int i = 0, j = npol - 1; i < npol; j = i++)
        {
            if ((((yp[i]<=y) && (y<yp[j])) || ((yp[j]<=y) && (y<yp[i]))) &&
                    (x > (xp[j] - xp[i]) * (y - yp[i]) / (yp[j] - yp[i]) + xp[i]))
                c = !c;
        }
        return c;
    }
}
