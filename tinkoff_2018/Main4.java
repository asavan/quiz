import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        int prev = -1;
        int diff = -1;
        for (int i = 0; i < n; ++i) {
            int a = scanner.nextInt();
            arr[i] = a;
            if (prev > 0) {
                int newDiff = a - prev;
                if (Math.abs(newDiff - diff) > 1 && diff > 0) {
                    System.out.print("kukuha");
                    return;
                }
                diff = newDiff;
            }
            prev = a;
        }

        double min = (double) (arr[n - 1] - arr[0] - 1) / (n-1);
        double max = (double) (arr[n - 1] + 1 - arr[0]) / (n-1);
        System.out.printf("%.3f %.3f", min, max);
    }

}