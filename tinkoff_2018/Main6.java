import java.util.Locale;
import java.util.Scanner;

public class Main6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.ENGLISH);
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();

        if (y > (0.5 * Math.abs(x) + 0.5)) {
            System.out.print("NO");
            return;
        }
        if (0.5 * x * x + y * y > 1) {
            System.out.print("NO");
            return;
        }

        if ((x - 0.5) * (x - 0.5) + y * y < 0.3) {
            System.out.print("NO");
            return;
        }

        if ((x + 0.5) * (x + 0.5) + y * y < 0.3) {
            System.out.print("NO");
            return;
        }
        System.out.print("YES");

    }

}