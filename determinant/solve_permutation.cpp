#include <iostream> 
#include <algorithm>
#include <utility>
#include <array>

namespace {
    static constexpr int SIZE = 3;
    static constexpr int SIZE_SQR = SIZE * SIZE;

    template <size_t _Size>
    int determinant(const std::array<std::array<int, _Size>, _Size>& a);

    template<>
    int determinant<3>(const std::array<std::array<int, 3>, 3>& a) {
        return
            a[0][0] * a[1][1] * a[2][2] +
            a[2][0] * a[0][1] * a[1][2] +
            a[1][0] * a[2][1] * a[0][2]
            - a[2][0] * a[1][1] * a[0][2]
            - a[0][1] * a[1][0] * a[2][2]
            - a[0][0] * a[1][2] * a[2][1];
    }

    template<>
    int determinant<2>(const std::array<std::array<int, 2>, 2>& a) {
        return a[0][0] * a[1][1] - a[1][0] * a[0][1];
    }


    void print(const std::array<std::array<int, SIZE>, SIZE>& matrix) {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                std::cout << matrix[i][j] << " ";
            }
            std::cout << std::endl;
        }
    }

    void print1(const std::array<int, SIZE_SQR>& a) {
        for (int i = 0; i < SIZE_SQR; ++i) {
            std::cout << a[i] << " ";
        }
        std::cout << std::endl;  
    }

    template <int ...I>
    constexpr auto init(std::index_sequence<I...>) {
        return std::array<int, sizeof...(I)>{I...};
    }
}

void solve_permutation() {
    std::array<std::array<int, SIZE>, SIZE> matrix = { 0 };
    int res = 0;
    std::array<int, SIZE_SQR> digits = init(std::make_index_sequence<SIZE_SQR>());
    do {
        // print1(digits);
        // std::array<int, SIZE_SQR> positions = init(std::make_index_sequence<SIZE_SQR>());
        // do {
            for (int i = 0; i < SIZE_SQR; ++i) {
                matrix[i / SIZE][i % SIZE] = digits[i] + 1;
            }
            int det = determinant(matrix);
            if (det > 0) {
                ++res;
            }
        // } while (std::next_permutation(begin(positions), end(positions)));
    }
    while (std::next_permutation(begin(digits), end(digits)));
    std::cout << "Best res " << res << std::endl;
}
