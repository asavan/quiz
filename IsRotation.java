// https://stackoverflow.com/questions/2553522/interview-question-check-if-one-string-is-a-rotation-of-other-string/2553533

public class IsRotation {
    public static boolean isRotation(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) {
            return false;
        }

        int i1 = 0;
        int i2 = 0;
        while (i1 < n && i2 < n) {
            char c1;
            char c2;
            int sameChars = 0;
            do {
                c1 = s1.charAt((i1 + sameChars) % n);
                c2 = s2.charAt((i2 + sameChars) % n);
                ++sameChars;
                if (sameChars > n) {
                    return true;
                }
            } while (c1 == c2);
            if (c1 > c2) {
                i1 += sameChars;
            } else {
                i2 += sameChars;
            }
        }
        return false;
    }

    public static boolean isRotation2(String s1, String s2) {
        return (s1.length() == s2.length()) && ((s1 + s1).contains(s2));
    }

    public static boolean isRotation3(String u, String v) {
        int n = u.length();
        int i = -1;
        int j = -1;
        if (n != v.length()) return false;

        while (i < n - 1 && j < n - 1) {
            int k = 1;
            while (k <= n && u.charAt((i + k) % n) == v.charAt((j + k) % n)) {
                k++;
            }
            if (k > n) {
                return true;
            }
            if (u.charAt((i + k) % n) > v.charAt((j + k) % n)) {
                i += k;
            } else {
                j += k;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        check(isRotation("tackoverflows", "ackoverflowst"));
        check(isRotation("tackoverflows", "overflowstack"));
        check(!isRotation("tackoverflows", "overflowstacc"));
        check(isRotation("aaabaaa", "aabaaaa"));
        check(isRotation("aaabaaa", "aaaaaba"));
        check(isRotation("HELLOHELLOHELLO1HELLOHELLOHELLO2", "HELLOHELLOHELLO2HELLOHELLOHELLO1"));
        check(!isRotation("HELLOHELLOHELLO1HELLOHELLOHELLO2", "HELLOHELLOHELLO1HELLOHELLOHELLO1"));

        check(isRotation2("tackoverflows", "overflowstack"));
        check(!isRotation2("tackoverflows", "overflowstacc"));
    }

    public static void check(boolean res) {
        if (!res) {
            System.out.println("WRONG ANSWER");
        }
    }
}
