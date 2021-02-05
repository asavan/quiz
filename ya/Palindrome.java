package ya;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by asavan on 05.02.2021.
 */
public class Palindrome {
    public static void main(String[] args) {
        boolean res = isPalindrome("'А роза упала на лапу Азора'");
        System.out.println(res);
    }

    @Test
    public void test() {
        assertTrue(isPalindrome("'А роза упала на лапу Азора'"));
        assertTrue(isPalindrome("Do geese see God?"));
        assertTrue(isPalindrome("Madam, I’m Adam"));
        assertFalse(isPalindrome("Бряк"));
        assertTrue(isPalindrome("Лазер Боре хер обрезал"));
        assertTrue(isPalindrome("Тор ебет тебе рот"));
        assertTrue(isPalindrome(""));
        assertTrue(isPalindrome("в"));
    }

    private static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char left = s.charAt(i);
            if (!Character.isAlphabetic(left)) {
                ++i;
                continue;
            }
            char right = s.charAt(j);
            if (!Character.isAlphabetic(right)) {
                --j;
                continue;
            }
            if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }
}
