import java.util.Scanner;

public class TinkSeptember3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.print(transform(s));
    }

    private static String transform(String s) {
        StringBuilder b = new StringBuilder();
        int currentCount = 0;
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (isDigit(c)) {
                currentCount *= 10;
                currentCount += digit(c);
            } else {
                if (currentCount == 0) {
                    b.append(c);
                } else {
                    for (int j = 0; j < currentCount; ++j) {
                        b.append(c);
                    }
                    currentCount = 0;
                }
            }
        }
        return b.toString();
    }

    private static int digit(char c) {
        return c - '0';
    }

    private static boolean isDigit(char c) {
        return Character.isDigit(c);
    }
}