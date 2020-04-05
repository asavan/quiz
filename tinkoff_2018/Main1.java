import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int m = scanner.nextInt();

        if (k == m) {
            System.out.print(0);
        }
        System.out.print(k + m - 1);
    }
}