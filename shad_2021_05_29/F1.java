package shad_2021_05_29;

/**
 * Created by asavan on 29.05.2021.
 */
public class F1 {

    public static void main(String[] args) {
        double max = 0;
        for (int i = 10000; i <= 99999; ++i ) {
            max = Math.max(max, (double) i / sum(i));
        }
        System.out.println(max);
    }

    static int sum(int n) {
        int s = 0;
        while(n != 0){
            //Суммирование цифр числа
            s += (n % 10);
            n/=10;
        }
        return s;
    }
}
