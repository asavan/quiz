/**
 * Created by asavan on 06.01.2019.
 */
public class Sheep {

    public static void main(String[] args) {
        long[] sheep = new long[2020];
        sheep[0] = -2;
        sheep[1] = 0;
        for (int i = 1; i < 2018; ++i) {
            sheep[i+1] = sheep[i] - sheep[i-1] + i*i;
            // System.out.print(" " + sum);
        }

        System.out.println(" " + sheep[2018]);
    }
}
