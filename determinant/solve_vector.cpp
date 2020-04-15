#include <iostream> 
#include <vector>
#include <string>
#include <algorithm>

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


    void print_vec(const std::vector<std::vector<int>>& matrix) {
        int size = matrix.size();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                std::cout << matrix[i][j] << " ";
            }
            std::cout << std::endl;
        }
    }

    inline bool is_first(int d) {
        return d % 2 == 0;
    }

    struct BestResult {
        std::vector<std::vector<int>> m;
        int i = -1;
        int j = -1;
        int k = -1;
        int result = INT_MAX;
        void print() {
            std::cout << "Best res " << result << std::endl;
            print_vec(m);
        }
    };

    /***
    * matrix - матрица в которой уже стоят какие-то числа, новое число можно ставить в клетки со значением 0
    * digits - булевый массив того какие числа уже заняты. false - число можно брать
    * d - номер хода. d == 0 - ход первого игрока
    * best1 - первый игрок не может выиграть больше best1 при идеальной игре второго игрока
    * best2 - второй игрок не может набрать меньше best2(он хочет набрать как можно меньше) при идеальной игре первого игрока
    * init_d - для какого номера хода надо получить ответ
    */
    BestResult who_wins(std::vector<std::vector<int>>& matrix, std::vector<bool>& digits, int d, int best1, int best2, int init_d) {

        int size = matrix.size();
        int size_sqr = size * size;
        BestResult answer;

        if (d == size_sqr) {
            int det = det_vec(matrix);
            //print_vec(matrix);
            //std::cout << "Det " << det << std::endl;

            if (d == init_d) {
                answer.m = matrix;
            }
            answer.result = det;
            return answer;
        }
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
                    int res = who_wins(matrix, digits, d + 1, best1, best2, init_d).result;
                    if (is_first(d)) {
                        if (best2 < res) {
                            best2 = res;
                            if (d == init_d) {
                                answer.i = i;
                                answer.j = j;
                                answer.k = k;
                                answer.m = matrix;
                            }
                            answer.result = res;
                        }
                        best2 = std::max(best2, res);
                    }
                    else {
                        if (best1 > res) {
                            best1 = res;
                            if (d == init_d) {
                                answer.i = i;
                                answer.j = j;
                                answer.k = k;
                                answer.m = matrix;
                            }
                            answer.result = res;
                        }
                        best1 = std::min(best1, res);
                    }

                    matrix[i][j] = 0;

                    if ((!is_first(d) && res <= best2) || (is_first(d) && res >= best1)) {
                        digits[k] = false;
                        if (d == init_d) {
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
        answer.result = is_first(d) ? best2 : best1;
        return answer;
    }
    
    BestResult solve_matrix(const std::vector<std::vector<int>>& matrix_) {
        std::vector<std::vector<int>> matrix = matrix_;
        int size = matrix.size();
        int size_sqr = size * size;

        std::vector<bool> digits(size_sqr, false);
        int d = 0;
        for (int i = 0; i < size_sqr; ++i) {
            int value = matrix[i / size][i % size];
            if (value != 0) {
                ++d;
                int index = value - 1;
                if (digits[index]) {
                    throw std::invalid_argument("reapited digit in matrix <" + std::to_string(value) + ">");
                }
                digits[index] = true;
            }
        }

        int best1 = INT_MAX;
        int best2 = INT_MIN;
        BestResult res = who_wins(matrix, digits, d, best1, best2, d);
        return res;
    }
}

void solve_vector() {
    std::vector<std::vector<int>> matrix = {
      {0, 0, 0},
      {0, 0, 0},
      {0, 0, 0}
    };

    try {
        BestResult res = solve_matrix(matrix);
        res.print();
    }
    catch (const std::exception& ex) {
        std::cerr << ex.what() << std::endl;
    }

}
