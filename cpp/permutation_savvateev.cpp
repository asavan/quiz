/*
https://www.youtube.com/watch?v=M3ifK-Gme1g

Требуется составить десятизначное число с различными цифрами, обладающее следующими свойствами: число, составленное из первых двух цифр этого числа, делится на 2, из первых трех цифр - на 3 и так далее. (Айбулат Абдуллин)
*/

#include <iostream> 
#include <algorithm>
#include <array>

namespace {
    bool check(const std::array<int, 9>& a) {
        int res = 0;
        for (int i = 0; i < 9; ++i) {
            res *= 10;
            res += (a[i] + 1);
            if (res % (i + 1) != 0) {
                return false;
            }
        }
        return true;
    }

    void print(const std::array<int, 9>& a) {
        for (int i = 0; i < 9; ++i) {
            std::cout << a[i] + 1;
        }
        std::cout << std::endl;  
    }

    template <int ...I>
    constexpr auto init(std::index_sequence<I...>) {
        return std::array<int, sizeof...(I)>{I...};
    }
}

void solve_permutation() {
    std::array<int, 9> digits = init(std::make_index_sequence<9>());
    do {
        if (check(digits)) {
            print(digits);
        }
    }
    while (std::next_permutation(begin(digits), end(digits)));
}

int main() {
    solve_permutation();
    return 0;
}
