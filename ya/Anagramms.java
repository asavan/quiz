/**
 * Created by asavan on 22.09.2019.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Anagramms {

    private static class Print {
        private int[] array;

        Print(String word) {
            array = new int[26];
            for (char c : word.toCharArray()) {
                array[c - 'a'] += 1;
            }
        }

        @Override
        public int hashCode() {
            int acc = 0;
            int i = 1;
            for (int c : array) {
                acc += c * i;
                ++i;
            }
            return acc;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (!(other instanceof Print)) return false;
            for (int i = 0; i < 26; ++i) {
                if (this.array[i] != ((Print)other).array[i]) {
                    return false;
                }
            }
            return true;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        String first = r.readLine();
        String second = r.readLine();
        if (new Print(first).equals(new Print(second))) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

}
