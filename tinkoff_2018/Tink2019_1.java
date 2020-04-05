import java.util.Scanner;

public class Tink2019_1 {

    static final int WAS_CAHR = 2;
    static final int DEFAULT = 0;
    static final int BEGIN = 1;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.print(transform(s));
    }

    private static String transform(String s) {
        StringBuilder b = new StringBuilder();
        int state = BEGIN;
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            switch (state) {
                case BEGIN:
                    if (c != ' ') {
                        b.append(c);
                        state = DEFAULT;
                    }
                    break;
                case DEFAULT:
                    if (c != ' ') {
                        b.append(c);
                    } else {
                        state = WAS_CAHR;
                    }
                    break;
                case WAS_CAHR:
                    if (c != ' ') {
                        b.append(' ').append(c);
                        state = DEFAULT;
                    }
            }
        }
        return b.toString();
    }
}
