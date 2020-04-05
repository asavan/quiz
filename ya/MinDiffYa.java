import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by asavan on 09.08.2018.
 */
public class MinDiffYa {
    public static void main1(String[] args) {
        int[] a = {20, 27, 1000, 1001};
        int[] b = {1, 1000, 1011};

        int res = minDiffF(a, b);
        System.out.println(res);

    }


    public static void main(String[] args) {
        int[] b = {2, 20};
        int[] a = {25, 1000};

        int res = minDiffF(a, b);
        System.out.println(res);

    }


    private static int minDiffF(int[] a, int[] b) {
        Iterator<Integer> ita = Arrays.stream(a).boxed().iterator();
        Iterator<Integer> itb = Arrays.stream(b).boxed().iterator();
        int aV = ita.next();
        int bV = itb.next();
        int diff = Math.abs(aV - bV);
        int mindiff = diff;

        boolean isA = true;
        while (ita.hasNext() || itb.hasNext()) {
            if (isA) {
                if (ita.hasNext()) {
                    aV = ita.next();
                } else {
                    isA = false;
                    continue;
                }
            } else {
                if (itb.hasNext()) {
                    bV = itb.next();
                } else {
                    isA = true;
                    continue;
                }
            }
            int diff2 = Math.abs(aV - bV);
            if (diff2 < diff) {
                if (mindiff > diff2) {
                    mindiff = diff2;
                }
            diff = diff2;
            } else {
                isA = !isA;
            }

        }

        return mindiff;
    }

}

