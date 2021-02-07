/**
 * Created by asavan on 06.02.2021.
 */
public class DoubleChecker {
    public static void main(String[] args) {
        for (int i = Integer.MAX_VALUE/2; i < Integer.MAX_VALUE; i++) {
            double test = i;
            double next = test+0.5;
            if (test == next) {
                System.out.println(i);
            }
        }
    }
}
