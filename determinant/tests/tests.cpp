#include "../solver/solver.h"
#include <cassert>
int main()
{

    std::vector<std::vector<int>> matrix = {
      {0, 0, 0},
      {0, 5, 0},
      {0, 0, 0}
    };

    BestResult res = solve_matrix(matrix);
    assert(res.result == 40);

    matrix = {
      {0, 0, 0},
      {0, 3, 0},
      {0, 0, 0}
    };

    res = solve_matrix(matrix);
    assert(res.result == -9);

    matrix = {
      {0, 0},
      {0, 0}
    };

    res = solve_matrix(matrix);
    assert(res.result == 1);
    return 0;
}
