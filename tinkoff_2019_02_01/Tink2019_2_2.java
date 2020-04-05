import java.util.Scanner;

public class Tink2019_2_2 {

    private static final int[] CHARS = new int[26];

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        calc(s);
        StringBuilder b = new StringBuilder();
        int i = 0;
        for (int count : CHARS) {
            if (count > 1) {
                b.append((char)('a'+i));
            }
            ++i;
        }
        System.out.println(b.toString());
    }

    private static void calc(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            CHARS[c - 'a']++;
        }
    }
}
