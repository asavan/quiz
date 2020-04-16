#include "solver.h"
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



int main()
{
    // mesure(solve_precompute, "solve_precompute");
    // mesure(solve_vector, "solve_vector");
    mesure(solve_c_array, "solve_c_array");
    mesure(solve_array, "solve_array");
    //mesure(solve_array, "solve_array");
    //mesure(solve_c_array, "solve_c_array");

    return 0;
}
