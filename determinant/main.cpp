#include <chrono>
#include <iostream> 
using namespace std::chrono;

void solve_array();
void solve_vector();
void solve_c_array();
void solve_permutation();
void solve_precompute();

int main()
{
    auto start = high_resolution_clock::now();
    solve_permutation();
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);
    std::cout << "overall time in ms " << duration.count() << std::endl;
    return 0;
}
