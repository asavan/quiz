/**
 * Created by asavan on 22.09.2019.
 */

import java.util.Scanner;

public class Ones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int maxLen = 0;
        int currLen = 0;
        for (int i = 0; i < n; i++) {
            int curr = scanner.nextInt();
            if (curr == 1) {
                ++currLen;
            } else {
                maxLen = Math.max(currLen, maxLen);
                currLen = 0;
            }
        }
        maxLen = Math.max(currLen, maxLen);
        System.out.println(maxLen);
    }
}
