public class MinInt {
    public static void main1(String[] args) {
        int min = Integer.MIN_VALUE;
        int minusMin = -min;
        System.out.print(min == minusMin);

        int zero = 0;
        int minusZero = -zero;
        System.out.print(zero == minusZero);
    }

    public static void main(String[] args) {
        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; ++i) {
            if (i == -i) {
                System.out.println(i);
            }
        }
    }
}
