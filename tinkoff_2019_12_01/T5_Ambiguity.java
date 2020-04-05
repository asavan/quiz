import java.util.Scanner;

public class T5_Ambiguity {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        int res = vars(s1, 0);
        System.out.println(res);
    }

    static int vars(String s, int i) {
        if (s.length() == i) {
            return 1;
        }
        if (s.length() == i+1) {
            return 1;
        }
        char curr = s.charAt(i);
        int digit = (curr - '0');
        if (digit == 0 || digit > 3) return vars(s, i+1);
        char next = s.charAt(i + 1);
        int nextDigit = next - '0';
        if (digit*10 + nextDigit >= 33) {
            return vars(s, i+1);
        }
        return vars(s, i+1) + vars(s, i+2);
    }
}
