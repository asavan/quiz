#include <chrono> 
#include <iostream> 
#include  <array>

static constexpr int SIZE = 2;
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

bool is_first(int d) {
    return ((SIZE_SQR - d) % 2) == 0;
}

int who_wins(std::array<std::array<int, SIZE>, SIZE> matrix, std::array<bool, SIZE_SQR> digits, int d) {
    int first = 0;
    if (d == 0) {
        // print(matrix);
        int det = determinant<SIZE>(matrix);
        if (det > 0) {
            //if (det == 10) {
            //    print(matrix);
            //}
            return det;
        }
        //else if (det < 0) {
        //    return 0;
        //}
        else {
            return 0;
        }
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
                int res = who_wins(matrix, digits, d - 1);
                matrix[i][j] = 0;
                if (!is_first(d) && res == 0) {
                    digits[k] = false;
                    first = 0;
                    return 0;
                }
                first = std::max(first, res);
            }
        }
        digits[k] = false;
    }
    return first;
}

using namespace std::chrono;
using namespace std;

int main()
{
    auto start = high_resolution_clock::now();
    auto stop = high_resolution_clock::now();
    std::array<std::array<int, SIZE>, SIZE> matrix = { 0 };
    std::array<bool, SIZE_SQR> digits = { false };
    long long res = who_wins(matrix, digits, SIZE_SQR);
    std::cout << res << std::endl;

    auto duration = duration_cast<milliseconds>(stop - start);
    cout << "overall time in ms " << duration.count() << endl;
    return 0;
}
