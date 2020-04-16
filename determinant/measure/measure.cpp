#include "measure.h"
#include <chrono>
#include <iostream> 


using namespace std::chrono;


void measure(BestResult(*f)(), const std::string& name)
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

void measure(BestResult(*f)(const std::vector<std::vector<int>>& matrix), const std::string& name, const std::vector<std::vector<int>>& matrix)
{
    auto start = high_resolution_clock::now();
    try {
        BestResult res = f(matrix);
        auto stop = high_resolution_clock::now();
        res.print();
        auto duration = duration_cast<milliseconds>(stop - start);
        std::cout << name << " overall time in ms " << duration.count() << std::endl;
    }
    catch (const std::exception& ex) {
        std::cerr << ex.what() << std::endl;
    }
}

void measure(const std::string& name, const std::vector<std::vector<int>>& matrix) {
    measure(solve_matrix, name, matrix);
}


void measure_int(int(*f)(), const std::string& name)
{
    auto start = high_resolution_clock::now();
    int res = f();

    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);

    std::cout << "Best res " << res << std::endl;
    std::cout << name << " overall time in ms " << duration.count() << std::endl;
}

