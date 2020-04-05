/**
 * Created by asavan on 22.09.2019.
 */

import java.io.IOException;

public class Duplicates {
    public static void main(String[] args) throws IOException {
        int n = read_int();
        if (n <= 0) {
            return;
        }
        int prev = read_int();
        for (int i = 1; i < n; i++) {
            int curr = read_int();
            if (curr != prev) {
                System.out.println(prev);
                prev = curr;
            }
//            if (n % 100000 == 0) {
//                System.gc();
//            }
        }
        System.out.println(prev);
    }

    public static int read_int() throws IOException
    {
        int number = 0;
        int signe = 1;

        int byteRead = System.in.read();
        while (byteRead != '-'  && ((byteRead < '0') || ('9' < byteRead)))
            byteRead = System.in.read();
        if (byteRead == '-'){
            signe = -1;
            byteRead = System.in.read();
        }
        while (('0' <= byteRead) && (byteRead <= '9')){
            number *= 10;
            number += byteRead - '0';
            byteRead = System.in.read();
        }
        return signe*number;
    }
}
