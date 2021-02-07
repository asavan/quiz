package ya.a1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by asavan on 07.02.2021.
 */
public class NormalizeSpace {
    public static void main(String[] args) {
        String string = "some    string  ";
        String normal = normalize(string);
        System.out.println(normal);
        System.out.println("some string ".equals(normal));
    }

    private static String normalize(String string) {
        char[] arr = string.toCharArray();
        int lettersEnd = 0;
        boolean isFirstSpace = true;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == ' ') {
                if (isFirstSpace) {
                    ++lettersEnd;
                }
                isFirstSpace = false;
            } else {
                if (lettersEnd != i) {
                    char temp = arr[lettersEnd];
                    arr[lettersEnd] = c;
                    arr[i] = temp;
                }
                ++lettersEnd;
                isFirstSpace = true;
            }
        }
        return new String(arr, 0, lettersEnd);
    }

    @Test
    public void test() {
        assertEquals("", normalize(""));
        assertEquals(" ", normalize(" "));
        assertEquals(" ", normalize("  "));
        assertEquals("b ", normalize("b  "));
        assertEquals("b", normalize("b"));
        assertEquals("some string ", normalize("some    string  "));
    }
}
