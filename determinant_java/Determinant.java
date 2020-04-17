package determinant_java;

public class Determinant {

    private static final int SIZE = 3;
    private static final int SIZE_SQR = SIZE * SIZE;
    private static final int INF = 512;
    private static final int MINUS_INF = -511;

    private static int determinant(char[][] a) {
        return a[0][0] * a[1][1] * a[2][2]
             + a[2][0] * a[0][1] * a[1][2]
             + a[1][0] * a[2][1] * a[0][2]
             - a[2][0] * a[1][1] * a[0][2]
             - a[0][1] * a[1][0] * a[2][2]
             - a[0][0] * a[1][2] * a[2][1];


    }

    private static boolean is_first(int step) {
        return step % 2 == 0;
    }

    private static int who_wins(char[][] matrix, boolean[] digits, int d, int best1, int best2) {

        if (d == SIZE_SQR) {
            return determinant(matrix);
        }

        for (char k = 0; k < SIZE_SQR; ++k) {
            if (digits[k]) {
                continue;
            }
            digits[k] = true;
            for (int i = 0; i < SIZE; ++i) {
                for (int j = 0; j < SIZE; ++j) {
                    if (matrix[i][j] != 0) {
                        continue;
                    }
                    matrix[i][j] = (char) (k + 1);
                    int res = who_wins(matrix, digits, d + 1, best1, best2);


                    if (is_first(d)) {
                        if (best2 < res) {
                            best2 = res;
                        }
                    } else {
                        if (best1 > res) {
                            best1 = res;
                        }
                    }

                    matrix[i][j] = 0;

                    if ((!is_first(d) && res <= best2) || (is_first(d) && res >= best1)) {
                        digits[k] = false;
                        return res;
                    }
                }
            }
            digits[k] = false;
        }
        return is_first(d) ? best2 : best1;
    }

    public static int solve_matrix_c(char[][] matrix) {
        boolean[] digits = new boolean[SIZE_SQR];
        int step = 0;
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                int value = matrix[i][j];
                if (value != 0) {
                    ++step;
                    int index = value - 1;
                    if (digits[index]) {
                        throw new RuntimeException("repeated digit " + value + " in matrix");
                    }
                    digits[index] = true;
                }
            }
        }

        return who_wins(matrix, digits, step, INF, MINUS_INF);
    }

    public static void main(String[] args) {
        char[][] table = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        long begin = System.currentTimeMillis();
        int res = solve_matrix_c(table);
        long end = System.currentTimeMillis();
        long diff = end - begin;
        System.out.println("Result " + res);
        System.out.println("Time " + diff);
    }
}
