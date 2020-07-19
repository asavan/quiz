#define INF 512
#define MINUS_INF -511
#define SIZE_SQR 9
char matrix[SIZE_SQR];
char digits[SIZE_SQR];

static inline char is_first(int step) {
    return step % 2 == 0;
}

static inline int sign(short res) {
    if (res < 0) {
        return -1;
    }
    return 1;
}

static inline abs(short res) {
    if (res < 0) {
        return -res;
    }
    return res;
}

static inline int getResult(char i, char k, short res) {
    int ans = i;
    ans += k * 10;
    ans += abs(res) * 100;
    return sign(res) * ans;
}

static inline short determinant() {
    return
        matrix[0] * matrix[4] * matrix[8] +
        matrix[6] * matrix[1] * matrix[5] +
        matrix[3] * matrix[7] * matrix[2]
        - matrix[2] * matrix[4] * matrix[6]
        - matrix[1] * matrix[3] * matrix[8]
        - matrix[0] * matrix[5] * matrix[7];
}

static short who_wins(char step, short best1, short best2) {

    if (step == SIZE_SQR) {
        return determinant();
    }

    char used_first_digit = 0;
    for (char k = 0; k < SIZE_SQR; ++k) {
        if (digits[k]) {
            continue;
        }
        if (step == SIZE_SQR - 2) {
            if (used_first_digit) {
                break;
            }
            used_first_digit = 1;
        }
        digits[k] = 1;
        for (char i = 0; i < SIZE_SQR; ++i) {
            if (matrix[i] != 0) {
                continue;
            }
            matrix[i] = k + 1;
            short res = who_wins(step + 1, best1, best2);


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
                digits[k] = 0;
                return res;
            }

        }
        digits[k] = 0;
    }
    return is_first(step) ? best2 : best1;
}

static int next_step(char step, short best1, short best2) {
    int answer = INF;

    for (char k = 0; k < SIZE_SQR; ++k) {
        if (digits[k]) {
            continue;
        }
        digits[k] = 1;
        for (char i = 0; i < SIZE_SQR; ++i) {
            if (matrix[i] != 0) {
                continue;
            }
            matrix[i] = k + 1;
            short res = who_wins(step + 1, best1, best2);

            if (is_first(step)) {
                if (best2 < res) {
                    best2 = res;
                    answer = getResult(i, k, res);
                }
            }
            else {
                if (best1 > res) {
                    best1 = res;
                    answer = getResult(i, k, res);
                }
            }
            matrix[i] = 0;
        }

        digits[k] = 0;
    }

    return answer;
}

int solve_matrix_web(int matrixNum) {
    char step = 0;
    for (int i = SIZE_SQR - 1; i >= 0; --i) {
        digits[i] = 0;
    }
    for (int i = SIZE_SQR - 1; i >= 0; --i) {
        char digit = matrixNum % 10;
        matrix[i] = digit;
        matrixNum /= 10;
        if (digit) {
            ++step;
            digits[digit - 1] = 1;
        }
    }
    return next_step(step, INF, MINUS_INF);
}
