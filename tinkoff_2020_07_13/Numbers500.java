package tinkoff_2020_07_13;

/**
 * Created by asavan on 13.07.2020.
 */
public class Numbers500 {
    public static void main(String[] args) {
        double sum = 0;
        for (int i = 1; i <= Math.pow(10, 10); ++i) {
            sum += Math.sqrt(Math.pow(10, 20) + i);
        }
        System.out.println(sum);
    }
}
