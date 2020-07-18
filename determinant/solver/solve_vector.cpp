#include "solver.h"
#include <vector>
#include <string>
#include <stdexcept>


namespace {

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

    int det_vec(const std::vector<std::vector<int>>& a) {
        if (a.size() == 2) {
            return determinant<2>(a);
        }
        if (a.size() == 3) {
            return determinant<3>(a);
        }
        // TODO
        return 0;
    }


    /***
    * matrix - матрица в которой уже стоят какие-то числа, новое число можно ставить в клетки со значением 0
    * digits - булевый массив того какие числа уже заняты. false - число можно брать
    * d - номер хода. d == 0 - ход первого игрока
    * best1 - первый игрок не может выиграть больше best1 при идеальной игре второго игрока
    * best2 - второй игрок не может набрать меньше best2(он хочет набрать как можно меньше) при идеальной игре первого игрока
    * need_fill_result - нужно ли запоминать матрицу лучшего хода
    */
    BestResult who_wins(std::vector<std::vector<int>>& matrix, std::vector<bool>& digits, int step, int best1, int best2, bool need_fill_result) {

        int size = matrix.size();
        int size_sqr = size * size;
        BestResult answer;

        if (step == size_sqr) {
            int det = det_vec(matrix);
            answer.result = det;
            return answer;
        }

        //// optimization may be delete
        if (step == 0 && size == 3) {
            answer.result = 40;
            if (need_fill_result) {
                answer.m = matrix;
                answer.j = 2;
                answer.i = 2;
                answer.k = 5;
                answer.m[answer.i][answer.j] = answer.k;
            }
            return answer;
        }
        /// end of optimization


        for (int k = 0; k < size_sqr; ++k) {
            if (digits[k]) {
                continue;
            }
            digits[k] = true;
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if (matrix[i][j] != 0) {
                        continue;
                    }
                    matrix[i][j] = k + 1;
                    int res = who_wins(matrix, digits, step + 1, best1, best2, false).result;
                    if (is_first(step)) {
                        if (best2 < res) {
                            best2 = res;
                            if (need_fill_result) {
                                answer.i = i;
                                answer.j = j;
                                answer.k = k;
                                answer.m = matrix;
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
                                answer.m = matrix;
                            }
                            answer.result = res;
                        }
                    }

                    matrix[i][j] = 0;

                    if ((!is_first(step) && res <= best2) || (is_first(step) && res >= best1)) {
                        digits[k] = false;
                        if (need_fill_result) {
                            answer.i = i;
                            answer.j = j;
                            answer.k = k;
                            answer.m = matrix;
                        }
                        answer.result = res;
                        return answer;
                    }
                }
            }

            digits[k] = false;
        }
        answer.result = is_first(step) ? best2 : best1;
        return answer;
    }

}

BestResult solve_matrix(const std::vector<std::vector<int>>& matrix_) {
    std::vector<std::vector<int>> matrix = matrix_;
    char size = matrix.size();

    std::vector<bool> digits(size * size, false);
    int step = 0;
    for (int i = 0; i < size; ++i) {
        for (int j = 0; j < size; ++j) {
            int value = matrix[i][j];
            if (value != 0) {
                ++step;
                int index = value - 1;
                if (digits[index]) {
                    throw std::invalid_argument("repeated digit in matrix <" + std::to_string(value) + ">");
                }
                digits[index] = true;
            }
        }
    }

    int best1 = INT_MAX;
    int best2 = INT_MIN;
    return who_wins(matrix, digits, step, best1, best2, true);
}


BestResult solve_vector() {
    std::vector<std::vector<int>> matrix = {
      {0, 0, 0},
      {0, 0, 0},
      {0, 0, 0}
    };

    return solve_matrix(matrix);
}

