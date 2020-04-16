namespace {

    static constexpr int SIZE = 3;
    static constexpr int SIZE_SQR = SIZE * SIZE;
    static constexpr int INF = 500;
    static constexpr int MINUS_INF = -500;

    template <int _Size>
    int determinant(const int(&a)[_Size][_Size]);

    template<>
    int determinant<3>(const int(&a)[3][3]) {
        return
            a[0][0] * a[1][1] * a[2][2] +
            a[2][0] * a[0][1] * a[1][2] +
            a[1][0] * a[2][1] * a[0][2]
            - a[2][0] * a[1][1] * a[0][2]
            - a[0][1] * a[1][0] * a[2][2]
            - a[0][0] * a[1][2] * a[2][1];
    }

    template<>
    int determinant<2>(const int(&a)[2][2]) {
        return a[0][0] * a[1][1] - a[1][0] * a[0][1];
    }

    inline bool is_first(int step) {
        return step % 2 == 0;
    }

    int who_wins(int(&matrix)[SIZE][SIZE], bool(&digits)[SIZE_SQR], int step, int best1, int best2) {

        if (step == SIZE_SQR) {
            return determinant<SIZE>(matrix);
        }

        for (int k = 0; k < SIZE_SQR; ++k) {
            if (digits[k]) {
                continue;
            }
            digits[k] = true;
            for (int i = 0; i < SIZE; ++i) {
                for (int j = 0; j < SIZE; ++j) {
                    if (matrix[i][j] != 0) {
                        continue;
                    }
                    matrix[i][j] = k + 1;
                    int res = who_wins(matrix, digits, step + 1, best1, best2);


                    if (is_first(step)) {
                        if (best2 < res) {
                            best2 = res;
                        }
                    }
                    else {
                        if (best1 > res) {
                            best1 = res;
                        }
                    }

                    matrix[i][j] = 0;

                    if ((!is_first(step) && res <= best2) || (is_first(step) && res >= best1)) {
                        digits[k] = false;
                        return res;
                    }
                }
            }
            digits[k] = false;
        }
        return is_first(step) ? best2 : best1;
    }
}

int solve_c_array() {
    int matrix[SIZE][SIZE]{};
    bool digits[SIZE_SQR] = { false };
    int best1 = INF;
    int best2 = MINUS_INF;
    return who_wins(matrix, digits, 0, best1, best2);
}
