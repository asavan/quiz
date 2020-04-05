import java.util.Scanner;

public class Tink_2019_1_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (isPalindromeFast(i)) {
                ++sum;
            }
        }
        System.out.println(sum);
    }

    private static boolean isPalindrome(int possiblePalindrome) {
        String stringRepresentation = String.valueOf(possiblePalindrome);
        return stringRepresentation.equals(new StringBuilder(stringRepresentation).reverse().toString());
    }

    private static boolean isPalindromeFast(int possiblePalindrome) {
        int next = 0;
        int pal = possiblePalindrome;

        while (pal != 0) {
            next = (next * 10) + (pal % 10);
            pal /= 10;
        }

        return possiblePalindrome == next;
    }
}
