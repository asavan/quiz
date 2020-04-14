#include <chrono>
#include <iostream> 
using namespace std::chrono;

void solve_array();
void solve_vector();
void solve_c_array();
void solve_permutation();

int main()
{
    auto start = high_resolution_clock::now();
    solve_c_array();
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<seconds>(stop - start);
    std::cout << "overall time in sec " << duration.count() << std::endl;
    return 0;
}
