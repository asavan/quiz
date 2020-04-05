/**
 * Created by asavan on 06.01.2019.
 */
public class Volume {

    public static void main(String[] args) {
        int sum = 0;
        for (int x = -100; x < 100; ++x) {
            for (int y = -100; y < 100; ++y) {
                for (int z = -100; z < 100; ++z) {
                    if ((Math.abs(x * y) <= 100) && (Math.abs(x * z) <= 100))
                        ++sum;
                    // }
                }
            }
            // System.out.print(" " + sum);
        }

        System.out.println(" " + sum);
    }
}
