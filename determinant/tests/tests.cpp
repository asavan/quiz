#include "../solver/solver.h"
#include <cassert>
#include <iostream>
#include <string>
namespace {
    void test(bool condition, const std::string& message = "") {
        if (!condition) {
            std::cerr << "Error " << message << std::endl;
        }
    }




    void case1() {
        std::vector<std::vector<int>> matrix = {
                                                  {0, 0, 0},
                                                  {0, 5, 0},
                                                  {0, 3, 0}
                                                        };

        BestResult res = solve_matrix(matrix);
        test(res.result == 90);

        matrix = {
          {0, 0, 0},
          {0, 3, 0},
          {0, 5, 0}
        };

        res = solve_matrix(matrix);
        test(res.result == 90);

        matrix = {
          {0, 0},
          {0, 0}
        };

        res = solve_matrix(matrix);
        test(res.result == 2);

    }

    void case2() {
        std::vector<std::vector<int>> matrix = {
                                              {0, 0, 0},
                                              {0, 0, 0},
                                              {0, 0, 0}
        };

        for (int i = 0; i < 9; i++)
        {
            matrix[i / 3][i % 3] = i + 1;
            // BestResult res = solve_matrix(matrix);
            BestResult res2 = solve_matrix_flat(matrix);
            BestResult res3 = solve_matrix_c(matrix);
            // test(res2.result == res.result);
            test(res2.result == res3.result);
            if (res2.result != res3.result) {
                res2.print();
                res3.print();
            }
            matrix[i / 3][i % 3] = 0;
        }
    }

    void case3() {
        std::vector<std::vector<int>> matrix = {
                                              {1, 2, 3},
                                              {4, 5, 7},
                                              {0, 0, 0}
        };

        BestResult res2 = solve_matrix_flat(matrix);
        BestResult res3 = solve_matrix_c(matrix);


        test(res2.result == res3.result);
        if (res2.result != res3.result) {
            res2.print();
            res3.print();
        }
    }

    void case4() {
        {
            std::vector<std::vector<int>> matrix = {
                                                  {0, 9, 0},
                                                  {0, 5, 0},
                                                  {0, 0, 0}
            };

            BestResult res = solve_matrix_flat(matrix);
            test(40 == res.result);
            res.print();
        }
        {
            std::vector<std::vector<int>> matrix = {
                                                  {1, 9, 0},
                                                  {0, 5, 4},
                                                  {0, 0, 0}
            };

            BestResult res = solve_matrix_flat(matrix);
            test(40 == res.result);
            res.print();
        }
        {
            std::vector<std::vector<int>> matrix = {
                                                  {1, 9, 0},
                                                  {0, 5, 4},
                                                  {0, 3, 2}
            };

            BestResult res = solve_matrix_flat(matrix);
            test(40 == res.result);
            res.print();
        }
        {
            std::vector<std::vector<int>> matrix = {
                                                  {1, 9, 6},
                                                  {7, 5, 4},
                                                  {0, 3, 2}
            };

            BestResult res = solve_matrix_flat(matrix);
            test(46 == res.result);
            res.print();
        }
        {
            std::vector<std::vector<int>> matrix = {
                                                  {1, 0, 0},
                                                  {0, 0, 0},
                                                  {0, 0, 0}
            };

            BestResult res = solve_matrix_flat(matrix);
            test(-33 == res.result);
            res.print();
        }



    }
}

int main()
{
    case4();
    return 0;
}
