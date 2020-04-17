#include "solver.h"
#include <string>
#include <stdexcept>

namespace {

    static constexpr char SIZE = 3;
    static constexpr char SIZE_SQR = SIZE * SIZE;
    static constexpr short INF = 512;
    static constexpr short MINUS_INF = -511;


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

    void copy_matrix(const short(&src)[SIZE_SQR], std::vector<std::vector<int>>& dst) {
        for (char i = 0; i < SIZE_SQR; ++i) {
            dst[i / 3][i % 3] = src[i];
        }
    }

    short who_wins(short(&matrix)[SIZE_SQR], bool(&digits)[SIZE_SQR], short step, short best1, short best2) {

        if (step == SIZE_SQR) {
            return determinant<SIZE>(matrix);
        }

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
                short res = who_wins(matrix, digits, step + 1, best1, best2);


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

    BestResult next_step(short(&matrix)[SIZE_SQR], bool(&digits)[SIZE_SQR], char step, short best1, short best2) {

        BestResult answer;
        answer.m = std::vector<std::vector<int>>(SIZE, std::vector<int>(SIZE, 0));

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
                    short res = who_wins(matrix, digits, step + 1, best1, best2);


                    if (is_first(step)) {
                        if (best2 < res) {
                            best2 = res;
                                answer.i = i;
                                answer.k = k;
                                copy_matrix(matrix, answer.m);
                            answer.result = res;
                        }
                    }
                    else {
                        if (best1 > res) {
                            best1 = res;
                                answer.i = i;
                                answer.k = k;
                                copy_matrix(matrix, answer.m);
                            answer.result = res;
                        }
                    }

                    matrix[i] = 0;
            }

            digits[k] = false;
        }
 
        return answer;
    }
}

BestResult solve_matrix_flat(const std::vector<std::vector<int>>& matrix_) {
    if (matrix_.size() != SIZE) {
        throw std::invalid_argument("wrong matrix size");
    }
    short matrix[SIZE_SQR];
    for (char i = 0; i < SIZE_SQR; ++i) {
        matrix[i] = matrix_[i / 3][i % 3];
    }
    bool digits[SIZE_SQR]{};
    char step = 0;
    for (char i = 0; i < SIZE_SQR; ++i) {
        short value = matrix[i];
        if (value != 0) {
            ++step;
            char index = value - 1;
            if (digits[index]) {
                throw std::invalid_argument("repeated digit in matrix <" + std::to_string(value) + ">");
            }
            digits[index] = true;
        }
    }

    short best1 = INF;
    short best2 = MINUS_INF;
    BestResult res = next_step(matrix, digits, step, best1, best2);
    return res;
}
