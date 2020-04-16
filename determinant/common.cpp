#include "solver.h"
#include <iostream> 

namespace {

    void print_vector(const std::vector<std::vector<int>>& matrix) {
        int size = matrix.size();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                std::cout << matrix[i][j] << " ";
            }
            std::cout << std::endl;
        }
    }

    template <int _Size>
    void print_array(const std::array<std::array<int, _Size>, _Size>& matrix) {
        for (int i = 0; i < _Size; ++i) {
            for (int j = 0; j < _Size; ++j) {
                std::cout << matrix[i][j] << " ";
            }
            std::cout << std::endl;
        }
    }

    template <int _Size>
    void print_c_array(const int(&matrix)[_Size][_Size]) {
        for (int i = 0; i < _Size; ++i) {
            for (int j = 0; j < _Size; ++j) {
                std::cout << matrix[i][j] << " ";
            }
            std::cout << std::endl;
        }
    }


}

void BestResult::print() {
    std::cout << "Best res " << result << std::endl;
    print_vector(m);
}
