import java.util.Scanner;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class T2_Palindrome {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        boolean res = isPalindrome(s);
        if (res) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static boolean isWord(char c) {
        return toLowerCase(c) != toUpperCase(c);
    }

    static boolean isEqualChars(char char1, char char2) {
        return toLowerCase(char1) == toLowerCase(char2);
    }

    static boolean isPalindrome(String s) {
        int start = 0,
                end = s.length() - 1;

        while (start < end) {
            char startChar = s.charAt(start),
                    endChar = s.charAt(end);

            if (!isWord(startChar)) {
                start++;
                continue;
            }

            if (!isWord(endChar)) {
                end--;
                continue;
            }

            if (!isEqualChars(startChar, endChar)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }
}
