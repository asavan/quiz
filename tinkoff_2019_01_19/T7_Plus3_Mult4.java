import java.util.Scanner;

public class T7_Plus3_Mult4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int res = minCount(x, y);
        System.out.println(res);
    }

    private static int minCount(int x, int y) {
        int min = minCount(x, y, 100, 0);
        if (min == 100) {
            return -1;
        }
        return min;
    }

    private static int minCount(int p, int y, int min, int curStep) {
        if (p > y) {
            return -1;
        }
        if (curStep > min) {
            return -1;
        }
        if (p == y) {
            return 0;
        }
        int plus3 = minCount(p + 3, y, min, curStep + 1);
        if (plus3 >= 0) {
            min = Math.min(min, plus3 + 1);
        }
        int mult4 = minCount(p*4, y, min, curStep + 1);
        if (mult4 >= 0) {
            min = Math.min(min, mult4 + 1);
        }
        return min;
    }
}
