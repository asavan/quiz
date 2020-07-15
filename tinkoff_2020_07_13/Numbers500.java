package tinkoff_2020_07_13;

/**
 * Created by asavan on 13.07.2020.
 */
public class Numbers500 {
    public static void main(String[] args) {
        double sum = 0;
        long count = 0;
        for (long i = 1; i <= 2* Math.pow(10, 10); ++i) {
            sum += Math.sqrt(Math.pow(10, 20) + i);
            if (count % 100_000_000 == 0) {
                System.out.println(count);
            }
            ++count;
        }
        System.out.println(sum);
    }
}
