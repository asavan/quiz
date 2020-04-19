#include "../measure/measure.h"

namespace {
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
            measure(solve_matrix_flat, "solve_matrix_flat", matrix);
            measure(solve_matrix_c, "solve_matrix_c", matrix);
            matrix[i / 3][i % 3] = 0;
        }
    }


    int case3()
    {
        std::vector<std::vector<int>> matrix = {
          {0, 0, 0},
          {0, 0, 0},
          {0, 0, 0}
        };

        measure_int(solve_array, "solve_array");
        measure_int(solve_c_array, "solve_c_array");
        measure_int(solve_vector_simple, "solve_vector_simple");
        measure(solve_precompute, "solve_precompute");
        measure(solve_matrix_flat, "solve_matrix_flat", matrix);
        measure(solve_vector, "solve_vector");
        measure_int(solve_array, "solve_array");
        measure_int(solve_vector_simple, "solve_vector_simple");
        measure_int(solve_c_array, "solve_c_array");

        return 0;
    }

    void case4()
    {
        std::vector<std::vector<int>> matrix = {
          {0, 0, 0},
          {0, 0, 0},
          {0, 0, 0}
        };

        for (int i = 0; i < 9; i++)
        {
            matrix[i / 3][i % 3] = i + 1;
            measure(solve_matrix_flat, "solve_matrix_flat", matrix);
            matrix[i / 3][i % 3] = 0;
        }

    }

    void case5()
    {
        std::vector<std::vector<int>> matrix = {
          {0, 0, 0},
          {0, 0, 0},
          {0, 0, 0}
        };

        measure(solve_matrix_flat, "solve_matrix_flat", matrix);
    }


}

int main() {
    case5();
    return 0;
}

