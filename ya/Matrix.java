import java.math.BigInteger;

/**
 * Created by asavan on 06.01.2019.
 */
public class Matrix {

    static class A {
        private static final int SIZE = 3;
        private BigInteger[][] m;

        A(BigInteger[][] m) {
            this.m = m;
        }

        A(int[][] m) {
            this.m = new BigInteger[SIZE][SIZE];
            for (int i = 0; i < SIZE; ++i) {
                for (int j = 0; j < SIZE; j++) {
                    this.m[i][j] = BigInteger.valueOf(m[i][j]);
                }
            }
        }

        A pow(int n) {
            A res = null;
            for (int i = 0; i < n; ++i) {
                res = mult(res, this);
            }
            return res;
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    b.append(m[i][j]).append(' ');
                }
                b.append('\n');
            }
            return b.toString();
        }

        BigInteger tr() {
            BigInteger sum = BigInteger.ZERO;
            for (int i = 0; i < SIZE; i++)
                sum = sum.add(m[i][i]);
            return sum;
        }

        private A mult(A a, A b) {
            BigInteger[][] c = new BigInteger[SIZE][SIZE];
            if (a == null) {
                for (int i = 0; i < SIZE; ++i) {
                    System.arraycopy(b.m[i], 0, c[i], 0, SIZE);
                }
            } else {
                for (int i = 0; i < SIZE; ++i) {
                    for (int j = 0; j < SIZE; j++) {
                        c[i][j] = BigInteger.ZERO;
                    }
                }
                for (int i = 0; i < SIZE; i++)
                    for (int j = 0; j < SIZE; j++)
                        for (int k = 0; k < SIZE; k++)
                            c[i][j] = c[i][j].add((a.m[i][k]).multiply(b.m[k][j]));
            }
            return new A(c);
        }
    }

    public static void main(String[] args) {
        int[][] m = {{8, -6, 3}, {5, -3, 3}, {4, -4, 5}};
        A a = new A(m);
        System.out.println(a);
        System.out.println(" " + a.tr());
        System.out.println(" " + a.pow(1).tr());
        System.out.println(a.pow(1));
        System.out.println(a.pow(2));
        System.out.println(a.pow(3));
        System.out.println("\n\n\n " + a.pow(2).tr());
//        System.out.println(a.pow(3).tr());
//        System.out.println(a.pow(4).tr());
//        System.out.println(a.pow(5).tr());
//        System.out.println(a.pow(6).tr());
//        System.out.println(a.pow(7).tr());
//        System.out.println(a.pow(8).tr());
//        System.out.println(a.pow(9).tr());
//        System.out.println(a.pow(10).tr());
//        System.out.println(a.pow(10).tr());
//        System.out.println(a.pow(11).tr());
//        System.out.println(a.pow(12).tr());
//        System.out.println(a.pow(13).tr());
//        System.out.println(a.pow(14).tr());
        System.out.println(a.pow(10).tr());
        System.out.println(a.pow(100).tr());
        System.out.println(a.pow(1000).tr());
    }
}
