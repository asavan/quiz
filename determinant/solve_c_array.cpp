#include "solver.h"
#include <string>
#include <stdexcept>

namespace {

    static constexpr int SIZE = 3;
    static constexpr int SIZE_SQR = SIZE * SIZE;

    template <size_t _Size>
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

    //void copy_matrix(const int(&src)[SIZE][SIZE], int(&dst)[SIZE][SIZE]) {
    //    for (int i = 0; i < SIZE; ++i) {
    //        for (int j = 0; j < SIZE; ++j) {
    //            dst[i][j] = src[i][j];
    //        }
    //    }
    //}

    void copy_matrix(const int(&src)[SIZE][SIZE], std::vector<std::vector<int>>& dst) {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                dst[i][j] = src[i][j];
            }
        }
    }

    int who_wins(int(&matrix)[SIZE][SIZE], bool(&digits)[SIZE_SQR], int d, int best1, int best2) {

        if (d == SIZE_SQR) {
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
                    int res = who_wins(matrix, digits, d + 1, best1, best2);
                    

                    if (is_first(d)) {
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

    BestResult next_step(int(&matrix)[SIZE][SIZE], bool(&digits)[SIZE_SQR], int d, int best1, int best2, bool need_fill_result) {

        BestResult answer;
        if (need_fill_result) {
            answer.m = std::vector<std::vector<int>>(SIZE, std::vector<int>(SIZE, 0));
        }

        if (d == SIZE_SQR) {
            if (need_fill_result) {
                copy_matrix(matrix, answer.m);
            }
            int det = determinant<SIZE>(matrix);
            answer.result = det;
            return answer;
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
                    // int res = next_step(matrix, digits, d + 1, best1, best2, false).result;
                    int res = who_wins(matrix, digits, d + 1, best1, best2);


                    if (is_first(d)) {
                        if (best2 < res) {
                            best2 = res;
                            if (need_fill_result) {
                                answer.i = i;
                                answer.j = j;
                                answer.k = k;
                                copy_matrix(matrix, answer.m);
                            }
                            answer.result = res;
                        }
                    }
                    else {
                        if (best1 > res) {
                            best1 = res;
                            if (need_fill_result) {
                                answer.i = i;
                                answer.j = j;
                                answer.k = k;
                                copy_matrix(matrix, answer.m);
                            }
                            answer.result = res;
                        }
                    }

                    matrix[i][j] = 0;

                    if ((!is_first(d) && res <= best2) || (is_first(d) && res >= best1)) {
                        digits[k] = false;
                        if (need_fill_result) {
                            answer.i = i;
                            answer.j = j;
                            answer.k = k;                            
                            copy_matrix(matrix, answer.m);
                        }
                        answer.result = res;
                        return answer;
                    }
                }
            }

            digits[k] = false;
        }
        answer.result = is_first(d) ? best2 : best1;
        return answer;
    }


}

BestResult solve_matrix(const std::vector<std::vector<int>>& matrix_) {
    if (matrix_.size() != SIZE) {
        throw std::invalid_argument("wrong matrix size");
    }
    int matrix[SIZE][SIZE];
    for (int i = 0; i < SIZE; ++i) {
        for (int j = 0; j < SIZE; ++j) {
            matrix[i][j] = matrix_[i][j];
        }
    }
    bool digits[SIZE_SQR]{};
    int step = 0;
    for (int i = 0; i < SIZE; ++i) {
        for (int j = 0; j < SIZE; ++j) {
            int value = matrix[i][j];
            if (value != 0) {
                ++step;
                int index = value - 1;
                if (digits[index]) {
                    throw std::invalid_argument("reapited digit in matrix <" + std::to_string(value) + ">");
                }
                digits[index] = true;
            }
        }
    }

    int best1 = INT_MAX;
    int best2 = INT_MIN;
    BestResult res = next_step(matrix, digits, step, best1, best2, true);
    return res;
}


int solve_c_array() {
    int matrix[SIZE][SIZE]{};
    bool digits[SIZE_SQR] = { false };
    int best1 = INT_MAX;
    int best2 = INT_MIN;
    return who_wins(matrix, digits, 0, best1, best2);
}

BestResult solve_precompute() {
    std::vector<std::vector<int>> matrix = {
      {0, 0, 0},
      {0, 0, 0},
      {0, 0, 0}
    };

    return solve_matrix(matrix);
}
