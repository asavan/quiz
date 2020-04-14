#include <iostream> 
#include <array>

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

inline bool is_first(int d) {
    return ((SIZE_SQR - d) % 2) == 0;
}

int who_wins(std::array<std::array<int, SIZE>, SIZE> matrix, std::array<bool, SIZE_SQR> digits, int d) {

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
                matrix[i][j] = 0;
                if (!is_first(d) && res <= 0) {
                    digits[k] = false;
                    return res;
                }
                if (is_first(d)) {
                    position_res = std::max(position_res, res);
                }
                else {
                    position_res = std::min(position_res, res);
                }
            }
        }
        if (is_first(d)) {
            first = std::max(first, position_res);
        }
        else {
            first = std::min(first, position_res);
        }
        if (d == SIZE_SQR) {
            std::cout << k + 1 << " " << position_res << std::endl;
        }
        digits[k] = false;
    }
    return first;
}

void solve() {
    std::array<std::array<int, SIZE>, SIZE> matrix = { 0 };
    std::array<bool, SIZE_SQR> digits = { false };
    int res = who_wins(matrix, digits, SIZE_SQR);
    std::cout << "Best res " << res << std::endl;
}

#include <chrono>
using namespace std::chrono;

int main()
{
    auto start = high_resolution_clock::now();
    solve();
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);
    std::cout << "overall time in ms " << duration.count() << std::endl;
    return 0;
}
