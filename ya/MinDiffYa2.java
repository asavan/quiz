/**
 * Created by asavan on 10.08.2018.
 */
public class MinDiffYa2 {

    public static void main(String[] args) {
        {
            int[] b = {2, 20};
            int[] a = {25, 1000};
            int res = minDiffF(a, b);
            System.out.println(res);
        }

        {
            int[] a = {20, 27, 1000, 1001};
            int[] b = {1, 1000, 1011};
            int res = minDiffF(a, b);
            System.out.println(res);

        }

        {
            int[] a = {2, 20};
            int[] b = {25, 1000};
            int res = minDiffF(a, b);
            System.out.println(res);
        }

        {
            int[] a = {28};
            int[] b = {25, 29};
            int res = minDiffF(a, b);
            System.out.println(res);
        }

        {
            int[] a = {2, 23, 29};
            int[] b = {25};
            int res = minDiffF(a, b);
            System.out.println(res);
        }
        {
            int[] a = {2};
            int[] b = {3};
            int res = minDiffF(a, b);
            System.out.println(res);
        }
    }


    private static int minDiffF(int[] a, int[] b) {
        int ita = 0;
        int itb = 0;
        int mindiff = Integer.MAX_VALUE;
        boolean isA = a.length > 1;
        while (ita < a.length || itb < b.length) {
            int aV = a[ita];
            int bV = b[itb];
            int diff = Math.abs(aV - bV);
            if (mindiff > diff) {
                mindiff = diff;
            }

            final int diff2;
            if (isA) {
                if (ita + 1 < a.length) {
                    int aVNew = a[ita + 1];
                    diff2 = Math.abs(aVNew - bV);
                } else {
                    return mindiff;
                }
            } else {
                if (itb + 1 < b.length) {
                    int bVNew = b[itb + 1];
                    diff2 = Math.abs(aV - bVNew);
                } else {
                    return mindiff;
                }
            }
            if (diff2 > diff) {
                isA = !isA;
            }
            if (isA) {
                ++ita;
                if (ita >= a.length) {
                    break;
                }
            } else {
                ++itb;
                if (itb >= b.length) {
                    break;
                }
            }
        }

        return mindiff;
    }

}

