#include <iostream> 
#include <vector>
#include <algorithm>

namespace {
    static constexpr int SIZE = 3;
    static constexpr int SIZE_SQR = SIZE * SIZE;

    template <size_t _Size>
    int determinant(const std::vector<std::vector<int>>& a);

    template<>
    int determinant<3>(const std::vector<std::vector<int>>& a) {
        return
            a[0][0] * a[1][1] * a[2][2] +
            a[2][0] * a[0][1] * a[1][2] +
            a[1][0] * a[2][1] * a[0][2]
            - a[2][0] * a[1][1] * a[0][2]
            - a[0][1] * a[1][0] * a[2][2]
            - a[0][0] * a[1][2] * a[2][1];
    }

    template<>
    int determinant<2>(const std::vector<std::vector<int>>& a) {
        return a[0][0] * a[1][1] - a[1][0] * a[0][1];
    }


    void print(const std::vector<std::vector<int>>& matrix) {
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

    int who_wins(std::vector<std::vector<int>> matrix, std::vector<bool> digits, int d) {

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
                    int res = who_wins(matrix, digits, d - 1);                    
                    if (!is_first(d) && res <= 0) {
                        return res;
                    }

                    if (is_first(d)) {
                        position_res = std::max(position_res, res);
                    }
                    else {
                        position_res = std::min(position_res, res);
                    }
                    if (is_first(d) && res >= 40 && d != SIZE_SQR) {
                        printResult(d, k, position_res, first);
                        return res;
                    }
                    matrix[i][j] = 0;
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

void solve_vector() {
    std::vector<std::vector<int>> matrix(SIZE, std::vector<int>(SIZE, 0));
    std::vector<bool> digits(SIZE_SQR, false);
    int res = who_wins(matrix, digits, SIZE_SQR);
    std::cout << "Best res " << res << std::endl;
}
