import java.util.Scanner;

public class Tink_2019_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += scanner.nextInt();
            }
        }
        System.out.println(sum/2);
    }
}
