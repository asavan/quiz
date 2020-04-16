#include <iostream> 
#include <algorithm>
#include <utility>
#include <array>
#include <set>
#include <climits>

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
    int positive_count = 0;
    int negative_count = 0;
    int max_det_count = 0;
    int min_det_count = 0;

    int zero_count = 0;
    int max_det = INT_MIN;
    int min_det = INT_MAX;
    std::set<int> dets;
    std::array<int, SIZE_SQR> digits = init(std::make_index_sequence<SIZE_SQR>());
    do {
        // print1(digits);
        // std::array<int, SIZE_SQR> positions = init(std::make_index_sequence<SIZE_SQR>());
        // do {
            for (int i = 0; i < SIZE_SQR; ++i) {
                matrix[i / SIZE][i % SIZE] = digits[i] + 1;
            }
            int det = determinant(matrix);
            dets.insert(det);
            if (det > 0) {
                ++positive_count;
                if (det > max_det) {
                    max_det = det;
                    max_det_count = 1;
                }
                else if (det == max_det) {
                    ++max_det_count;
                }
            }
            else if (det < 0) {
                ++negative_count;
                if (det < min_det) {
                    min_det = det;
                    min_det_count = 1;
                }
                else if (det == min_det) {
                    ++min_det_count;
                }

            }
            else {
                ++zero_count;
            }
        // } while (std::next_permutation(begin(positions), end(positions)));
    }
    while (std::next_permutation(begin(digits), end(digits)));
    std::cout << "Max det " << *dets.rbegin() << std::endl;
    std::cout << "Min det " << *dets.begin() << std::endl;
    std::cout << "Positive count " << positive_count << std::endl;
    std::cout << "Negative count " << negative_count << std::endl;
    std::cout << "Zero count " << zero_count << std::endl;

    std::cout << "Max det count " << max_det_count << std::endl;
    std::cout << "Min det count " << min_det_count << std::endl;

    for (int det : dets) {
        std::cout << det << " ";
    }
    std::cout << std::endl;
    std::cout << "det size " << dets.size() << std::endl;

    int prev = 0;
    int hole_count = 0;
    for (int det : dets) {
        if (det > 0) {
            if (det - prev > 1) {
                std::cout <<" hole " << det << std::endl;
                ++hole_count;
                // break;
            }
        }
        prev = det;
        
    }
    std::cout << "Hole count " << hole_count << std::endl;

}
