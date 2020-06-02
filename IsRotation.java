// https://stackoverflow.com/questions/2553522/interview-question-check-if-one-string-is-a-rotation-of-other-string/2553533

public class IsRotation {
    public static boolean isRotation(String u, String v) {
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

    public static boolean isRotation2(String s1,String s2) {
        return (s1.length() == s2.length()) && ((s1 + s1).contains(s2));
    }

    public static void main(String[] args) {
        check(isRotation("tackoverflows", "ackoverflowst"));
        check(isRotation("tackoverflows", "overflowstack"));
        check(!isRotation("tackoverflows", "overflowstacc"));

        check(isRotation2("tackoverflows", "overflowstack"));
        check(!isRotation2("tackoverflows", "overflowstacc"));
    }

    public static void check(boolean res) {
        if (!res) {
            System.out.println("WRONG ANSWER");
        }
    }
}
