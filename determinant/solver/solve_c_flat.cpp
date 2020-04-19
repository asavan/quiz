#include "solver.h"
#include <string>
#include <stdexcept>

namespace {
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

    template <char _SIZE_SQR>
    void copy_matrix(const short(&src)[_SIZE_SQR], std::vector<std::vector<int>>& dst) {
        for (char i = 0; i < _SIZE_SQR; ++i) {
            dst[i / 3][i % 3] = src[i];
        }
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
        answer.m = std::vector<std::vector<int>>(_Size, std::vector<int>(_Size, 0));

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
                    copy_matrix<SIZE_SQR>(matrix, answer.m);
                    answer.result = res;
                }

                matrix[i] = 0;
            }

            digits[k] = false;
        }

        return answer;
    }

    template <char _Size>
    BestResult solve_matrix_flat_(const std::vector<std::vector<int>>& matrix_) {
        if (matrix_.size() != _Size) {
            throw std::invalid_argument("wrong matrix size");
        }
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
                if (digits[index]) {
                    throw std::invalid_argument("repeated digit in matrix <" + std::to_string(value) + ">");
                }
                digits[index] = true;
            }
        }

        short best1 = INF;
        short best2 = MINUS_INF;
        BestResult res = next_step<_Size>(matrix, digits, step, best1, best2);
        return res;
    }

}

BestResult solve_matrix_flat(const std::vector<std::vector<int>>& matrix) {
    if (matrix.size() == 2) {
        return solve_matrix_flat_<2>(matrix);
    }
    if (matrix.size() == 3) {
        return solve_matrix_flat_<3>(matrix);
    }
    throw std::invalid_argument("wrong matrix size");
}
