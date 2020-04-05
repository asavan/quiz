import java.util.Scanner;

public class TT2_Password {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        boolean res = isGoodPass(s);
        if (res) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean isGoodPass(String s) {
        if (s.length() < 8) {
            return false;
        }
        boolean hasLow = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hasDigit |= Character.isDigit(c);
            hasUpper |= Character.isUpperCase(c);
            hasLow |= Character.isLowerCase(c);
            if (hasDigit && hasLow && hasUpper) {
                return true;
            }
        }
        return false;
    }
}
