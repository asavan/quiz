
public class Prime {

    public static void main(String[] args) {

        int start = 524;
        int[] arr = new int[17];
        for (int i = 0; i < 17; i++) {
            int ch = start + i;
            arr[i] = ch;
            System.out.println(ch);
        }

        int count = 0;
        for (int ch : arr) {
            for (int ch2 : arr) {
                if (ch2 == ch) {
                    continue;
                }
                int nod = gcd(ch, ch2);
                if (nod > 1) {
                    System.out.print(ch);
                    System.out.print(" ");
                    System.out.print(ch2);
                    System.out.print(" ");
                    System.out.println(nod);
                    ++count;
                    break;
                }
            }

        }

        System.out.println(count);

    }


    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}