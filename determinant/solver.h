#pragma once
#include <vector>
#include <climits>

struct BestResult {
    std::vector<std::vector<int>> m;
    int i = -1;
    int j = -1;
    int k = -1;
    int result = INT_MAX;
    void print();
};

int solve_c_array();
int solve_array();

BestResult solve_vector();
BestResult solve_precompute();

void solve_permutation();

BestResult solve_matrix(const std::vector<std::vector<int>>& matrix);

inline bool is_first(int step) {
    return step % 2 == 0;
}
