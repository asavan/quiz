#include <iostream>

extern "C" int solve_matrix_web(int matrixNum);

int main(int matrixNum)
{
    std::cout << solve_matrix_web(90054000) << std::endl;
    std::cout << solve_matrix_web(0) << std::endl;
    std::cout << solve_matrix_web(1) << std::endl;
}
