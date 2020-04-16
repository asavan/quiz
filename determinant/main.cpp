#include "solver/solver.h"
#include <chrono>
#include <iostream> 

using namespace std::chrono;


void mesure(BestResult(*f)(), const std::string& name)
{
    auto start = high_resolution_clock::now();
    try {
        BestResult res = f();
        auto stop = high_resolution_clock::now();
        res.print();
        auto duration = duration_cast<milliseconds>(stop - start);
        std::cout << name << " overall time in ms " << duration.count() << std::endl;
    }
    catch (const std::exception& ex) {
        std::cerr << ex.what() << std::endl;
    }
}

void mesure_int(int(*f)(), const std::string& name)
{
    auto start = high_resolution_clock::now();
    int res = f();

    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);

    std::cout << "Best res " << res << std::endl;
    std::cout << name << " overall time in ms " << duration.count() << std::endl;
}

int main()
{
    mesure_int(solve_array, "solve_array");
    mesure_int(solve_c_array, "solve_c_array");
    mesure_int(solve_vector_simple, "solve_vector_simple");
    mesure(solve_precompute, "solve_precompute");
    mesure(solve_vector, "solve_vector");
    mesure_int(solve_array, "solve_array");
    mesure_int(solve_vector_simple, "solve_vector_simple");
    mesure_int(solve_c_array, "solve_c_array");

    return 0;
}
