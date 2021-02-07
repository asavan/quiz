package ya.a1;

/**
 * Created by asavan on 07.02.2021.
 */
public class Urlify {
    public static void main(String[] args) {
        String input = "my url";
        char[] string = new char[100];
        for (int i = 0; i < input.length(); i++) {
            string[i] = input.charAt(i);
        }
        int res = urlify(string, 6);
        System.out.println(res);
        System.out.println(string);

    }

    public static int urlify(char[] string, int n) {
        int spaceCount = 0;
        for (int i = 0; i < n; i++) {
            if (string[i] == ' ') {
                ++spaceCount;
            }
        }
        int newSize = n + 2*spaceCount;
        int currIndex = newSize;
        for (int i = n-1; i >= 0; --i) {
            if (string[i] == ' ') {
                string[--currIndex] = '0';
                string[--currIndex] = '2';
                string[--currIndex] = '%';
            } else {
                string[--currIndex] = string[i];
            }
        }
        return newSize;
    }
}
