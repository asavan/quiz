

namespace {

    static constexpr short INF = 512;
    static constexpr short MINUS_INF = -511;


    struct BestResult {
        int i = -1;
        int k = -1;
        int result = INF;
    };

    inline bool is_first(int step) {
        return step % 2 == 0;
    }

    template <char _Size>
    short determinant(const short(&a)[_Size * _Size]);

    template<>
    short determinant<3>(const short(&a)[9]) {
        return
            a[0] * a[4] * a[8] +
            a[6] * a[1] * a[5] +
            a[3] * a[7] * a[2]
            - a[2] * a[4] * a[6]
            - a[1] * a[3] * a[8]
            - a[0] * a[5] * a[7];
    }

    template<>
    short determinant<2>(const short(&a)[4]) {
        return a[0] * a[3] - a[1] * a[2];
    }


    template <char _Size>
    short who_wins(short(&matrix)[_Size * _Size], bool(&digits)[_Size * _Size], short step, short best1, short best2) {
        constexpr char SIZE_SQR = _Size * _Size;

        if (step == SIZE_SQR) {
            return determinant<_Size>(matrix);
        }

        bool used_first_digit = false;
        for (char k = 0; k < SIZE_SQR; ++k) {
            if (digits[k]) {
                continue;
            }
            if (step == SIZE_SQR - 2) {
                if (used_first_digit) {
                    break;
                }
                used_first_digit = true;
            }
            digits[k] = true;
            for (char i = 0; i < SIZE_SQR; ++i) {
                if (matrix[i] != 0) {
                    continue;
                }
                matrix[i] = k + 1;
                short res = who_wins<_Size>(matrix, digits, step + 1, best1, best2);


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

                matrix[i] = 0;

                if ((!is_first(step) && res <= best2) || (is_first(step) && res >= best1)) {
                    digits[k] = false;
                    return res;
                }

            }
            digits[k] = false;
        }
        return is_first(step) ? best2 : best1;
    }

    template <char _Size>
    BestResult next_step(short(&matrix)[_Size * _Size], bool(&digits)[_Size * _Size], char step, short best1, short best2) {
        constexpr char SIZE_SQR = _Size * _Size;
        BestResult answer;

        for (char k = 0; k < SIZE_SQR; ++k) {
            if (digits[k]) {
                continue;
            }
            digits[k] = true;
            for (char i = 0; i < SIZE_SQR; ++i) {
                if (matrix[i] != 0) {
                    continue;
                }
                matrix[i] = k + 1;
                // int res = next_step(matrix, digits, d + 1, best1, best2, false).result;
                short res = who_wins<_Size>(matrix, digits, step + 1, best1, best2);

                bool need_feel_matrix = false;
                if (is_first(step)) {
                    if (best2 < res) {
                        best2 = res;
                        need_feel_matrix = true;
                    }
                }
                else {
                    if (best1 > res) {
                        best1 = res;
                        need_feel_matrix = true;
                    }
                }
                if (need_feel_matrix) {
                    answer.i = i;
                    answer.k = k;
                    answer.result = res;
                }

                matrix[i] = 0;
            }

            digits[k] = false;
        }

        return answer;
    }

    template <char _Size>
    BestResult solve_matrix_flat_(int(&matrix_)[_Size][_Size] ) {
        constexpr char SIZE_SQR = _Size * _Size;
        short matrix[SIZE_SQR];
        for (char i = 0; i < SIZE_SQR; ++i) {
            matrix[i] = matrix_[i / _Size][i % _Size];
        }
        bool digits[SIZE_SQR]{};
        char step = 0;
        for (char i = 0; i < SIZE_SQR; ++i) {
            short value = matrix[i];
            if (value != 0) {
                ++step;
                char index = value - 1;
                digits[index] = true;
            }
        }

        short best1 = INF;
        short best2 = MINUS_INF;
        BestResult res = next_step<_Size>(matrix, digits, step, best1, best2);
        return res;
    }

    template <char SIZE_SQR>
    void intToMatrix(int matrixNum, short(&matrix)[SIZE_SQR]) {
        for (int i = SIZE_SQR - 1; i >= 0; --i) {
            int digit = matrixNum % 10;
            matrix[i] = digit;
            matrixNum /= 10;
        }
    }

    template <char _Size>
    BestResult solve_matrix_flat2_(int matrixNum) {
        constexpr char SIZE_SQR = _Size * _Size;
        short matrix[SIZE_SQR];
        intToMatrix(matrixNum, matrix);
        bool digits[SIZE_SQR]{};
        char step = 0;
        for (char i = 0; i < SIZE_SQR; ++i) {
            short value = matrix[i];
            if (value != 0) {
                ++step;
                char index = value - 1;
                digits[index] = true;
            }
        }

        short best1 = INF;
        short best2 = MINUS_INF;
        BestResult res = next_step<_Size>(matrix, digits, step, best1, best2);
        return res;
    }


}

#ifdef __cplusplus
extern "C" {
#endif
int solve_matrix_web(int matrixNum) {
    BestResult res =  solve_matrix_flat2_<3>(matrixNum);
    int ans = res.i;
    ans += res.k * 10;
    ans += res.result * 100;
    return ans;
}

#ifdef __cplusplus
}
#endif
