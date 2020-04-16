#include "../solver/solver.h"
#include <cassert>
int main()
{

    std::vector<std::vector<int>> matrix = {
      {0, 0, 0},
      {0, 5, 0},
      {0, 3, 0}
    };

    BestResult res = solve_matrix(matrix);
    assert(res.result == 90);

    matrix = {
      {0, 0, 0},
      {0, 3, 0},
      {0, 5, 0}
    };

    res = solve_matrix(matrix);
    assert(res.result == 90);

    matrix = {
      {0, 0},
      {0, 0}
    };

    res = solve_matrix(matrix);
    assert(res.result == 2);
    return 0;
}
