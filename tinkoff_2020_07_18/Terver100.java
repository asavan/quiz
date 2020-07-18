package tinkoff_2020_07_18;

/**
 * Created by asavan on 13.07.2020.
 */
public class Terver100 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1200021));
        int i = 0;
        int count = 0;
        for (i = 1000000; i < 9999999; ++i) {
            if (isPalindrome(i)) {
                ++count;
            }
        }
        System.out.println(count);
        System.out.println(i);
        System.out.println((double)count*1000/(i-1));
    }

    public static boolean isPalindrome(int integer) {
        int palindrome = integer;
        int reverse = 0;

        // Compute the reverse
        while (palindrome != 0) {
            int remainder = palindrome % 10;
            reverse = reverse * 10 + remainder;
            palindrome = palindrome / 10;
        }

        // The integer is palindrome if integer and reverse are equal
        return integer == reverse; // Improved by Peter Lawrey

    }

    private static boolean isPalindrome1(int i) {
        for (int j = 0; j < 3; ++j) {
            int biggest = i / ((int) Math.pow(10, 6 - j));
            if (i % 10 != biggest) {
                return false;
            }
            i -= biggest * (int) Math.pow(10, 6 - j);
            i /=10;
        }
        return true;
    }

}
