
public class Prime2 {

    public static void main(String[] args) {

        for (int i = 1; i < 3000; ++i) {
            int count = getCount(i);
            if (count >= 17) {
                System.out.print(i);
                System.out.print(" ");
                System.out.println(count);
                break;
            }
        }


    }

    private static int getCount(int start) {
        int[] arr = new int[17];
        for (int i = 0; i < 17; i++) {
            int ch = start + i;
            arr[i] = ch;
        }

        int count = 0;
        for (int ch : arr) {
            if (ch % 2 == 0) {
                ++count;
                continue;
            }
            for (int ch2 : arr) {
                if (ch2 == ch) {
                    continue;
                }
                int nod = gcd(ch, ch2);
                if (nod > 1) {
                    ++count;
                    break;
                }
            }

        }
        return count;
    }


    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}