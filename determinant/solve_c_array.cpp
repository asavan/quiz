#include <iostream> 
#include <algorithm>

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


    void print(const int(&matrix)[2][2]) {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                std::cout << matrix[i][j] << " ";
            }
            std::cout << std::endl;
        }
    }

    inline bool is_first(int d) {
        return d % 2 == 0;
    }

    void printResult(int d, int k, int position_res, int best1, int best2)
    {
        if (d <= 0) {
            std::cout << k + 1 << " " << position_res << " " << best1 << " " << best2 << std::endl;
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
                    matrix[i][j] = 0;
                    if (!is_first(d) && res <= best2) {
                        // printResult(d, k, position_res, best1, best2);
                        digits[k] = false;
                        return res;
                    }

                    if (is_first(d)) {
                        best2 = std::max(best2, res);
                    }
                    else {
                        best1 = std::min(best1, res);
                    }
                    if (is_first(d) && res >= best1) {
                        digits[k] = false;
                        // printResult(d, k, position_res, best1, best2);
                        return res;
                    }
                }
            }

            // printResult(d, k, position_res, best1, best2);
            digits[k] = false;
        }
        return is_first(d) ? best2 : best1;
    }

    template <size_t _Size>
    int solve_matrix(const int(&matrix_)[_Size][_Size]) {
        int matrix[_Size][_Size];
        for (int i = 0; i < _Size; ++i) {
            for (int j = 0; j < _Size; ++j) {
                matrix[i][j] = matrix_[i][j];
            }
        }
        bool digits[SIZE_SQR]{};
        int d = 0;
        for (int i = 0; i < SIZE_SQR; ++i) {
            int value = matrix[i / 3][i % 3];
            if (value != 0) {
                ++d;
                digits[value - 1] = true;
            }
        }

        int best1 = INT_MAX;
        int best2 = INT_MIN;
        int res = who_wins(matrix, digits, d, best1, best2);
        return res;
    }
}

void solve_c_array() {
    int matrix[SIZE][SIZE]{};
    bool digits[SIZE_SQR] = { false };
    int best1 = INT_MAX;
    int best2 = INT_MIN;
    int res = who_wins(matrix, digits, 0, best1, best2);
    std::cout << "Best res " << res << std::endl;
}

void solve_precompute() {
    int matrix[3][3] =
    {
      {0, 0, 0},
      {0, 0, 3},
      {0, 0, 0}
    };

    int res = solve_matrix<3>(matrix);
    std::cout << "Best res " << res << std::endl;
}
