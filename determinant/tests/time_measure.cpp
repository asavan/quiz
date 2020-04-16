#include "../measure/measure.h"

int main()
{
    measure_int(solve_array, "solve_array");
    measure_int(solve_c_array, "solve_c_array");
    measure_int(solve_vector_simple, "solve_vector_simple");
    measure(solve_precompute, "solve_precompute");
    measure(solve_vector, "solve_vector");
    measure_int(solve_array, "solve_array");
    measure_int(solve_vector_simple, "solve_vector_simple");
    measure_int(solve_c_array, "solve_c_array");

    return 0;
}
