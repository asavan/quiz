#include <iostream> 
#include <algorithm>

namespace {

    static constexpr int SIZE = 3;
    static constexpr int SIZE_SQR = SIZE * SIZE;

    template <size_t _Size>
    int determinant(const int (&a)[_Size][_Size]);

    template<>
    int determinant<3>(const int (&a)[3][3]) {
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
        return ((SIZE_SQR - d) % 2) == 0;
    }

    void printResult(int d, int k, int position_res, int first)
    {
        if (d == SIZE_SQR) {
            std::cout << k + 1 << " " << position_res << std::endl;
        }
    }

    int who_wins(int(&matrix)[SIZE][SIZE], bool (&digits)[SIZE_SQR], int d, int best1, int best2) {

        if (d == 0) {
            return determinant<SIZE>(matrix);
        }
        int first = is_first(d) ? INT_MIN : INT_MAX;
        for (int k = 0; k < SIZE_SQR; ++k) {
            if (digits[k]) {
                continue;
            }
            digits[k] = true;
            int position_res = is_first(d) ? INT_MIN : INT_MAX;
            for (int i = 0; i < SIZE; ++i) {
                for (int j = 0; j < SIZE; ++j) {
                    if (matrix[i][j] != 0) {
                        continue;
                    }
                    matrix[i][j] = k + 1;
                    int res = who_wins(matrix, digits, d - 1, best1, best2);
                    matrix[i][j] = 0;
                    if (!is_first(d) && res <= best2) {
                        digits[k] = false;
                        return res;
                    }

                    if (is_first(d)) {
                        position_res = std::max(position_res, res);
                        best2 = std::max(best2, res);
                    }
                    else {
                        position_res = std::min(position_res, res);
                        best1 = std::min(res, best1);
                    }
                    if (is_first(d) && res >= best1) {
                        digits[k] = false;
                        printResult(d, k, position_res, first);
                        return res;
                    }
                }
            }
            if (is_first(d)) {
                first = std::max(first, position_res);
            }
            else {
                first = std::min(first, position_res);
            }
            printResult(d, k, position_res, first);
            digits[k] = false;
        }
        return first;
    }
}

void solve_c_array() {
    int matrix[SIZE][SIZE]{};
    bool digits[SIZE_SQR] = { false };
    int best1 = INT_MAX;
    int best2 = INT_MIN;
    int res = who_wins(matrix, digits, SIZE_SQR, best1, best2);
    std::cout << "Best res " << res << std::endl;
}
