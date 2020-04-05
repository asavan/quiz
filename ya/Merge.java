/**
 * Created by asavan on 22.09.2019.
 */

import java.io.IOException;


public class Merge {
    public static void main(String[] args) throws IOException {
        // Scanner scanner = new Scanner(System.in);
        int k = read_int();
        int [] res = new int[101];
        for (int i = 0; i < k; i++) {
            int n = read_int();
            for (int j = 0; j < n; ++j) {
                int curr = read_int();
                res[curr]++;
            }
        }
        for (int j = 0; j < 101; ++j) {
            int re = res[j];
            for (int i = 0; i < re; i++) {
                System.out.print(j);
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static int read_int() throws IOException
    {
        int number = 0;

        int byteRead = System.in.read();
        while (byteRead != '-'  && ((byteRead < '0') || ('9' < byteRead)))
            byteRead = System.in.read();
        while (('0' <= byteRead) && (byteRead <= '9')){
            number *= 10;
            number += byteRead - '0';
            byteRead = System.in.read();
        }
        return number;
    }

}
