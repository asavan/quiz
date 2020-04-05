import java.util.Scanner;

public class T1_Reverse {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        int [] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = i+1;
        }
        reverse(arr, x1-1, y1-1);
        reverse(arr, x2-1, y2-1);
        print(n, arr);

    }

    private static void print(int n, int[] arr) {
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
    }

    static void reverse(int [] arr, int b, int e) {
        for (int i = b; i <= (e+b)/2; ++i) {
            int t = arr[i];
            arr[i] = arr[e-i+b];
            arr[e-i+b] = t;
        }
    }
}
