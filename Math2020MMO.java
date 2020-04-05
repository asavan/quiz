/**
 * Created by asavan on 05.03.2020.
 */
public class Math2020MMO {
    private static int p(int x) {
        return 11 * x * x - 110 * x + 165;
    }

    private static int right(int x) {
        return 121 * x * x;
    }

    private static int left(int x) {
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            sum += p(x + i);
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = -20; i < 50; i++) {
            boolean res = left(i) == right(i);
            if (!res) {
                System.out.println(i);
            }
        }
    }
}
