#include "measure/measure.h"

void m4()
{
    std::vector<std::vector<int>> matrix = {
      {0, 0, 0},
      {0, 8, 0},
      {0, 0, 2}
    };
    measure("m4", matrix);
}


void m3()
{
    std::vector<std::vector<int>> matrix = {
      {0, 0, 0},
      {0, 8, 0},
      {0, 0, 0}
    };
    int size = matrix.size();
    int size_sqr = size * size;
    for (int i = 0; i < size_sqr; ++i) {
        if (i == 4) {
            continue;
        }
        int y = i / size;
        int x = i % size;
        matrix[y][x] = 1;
        measure("m3", matrix);
        matrix[y][x] = 0;
    }
}

void m2()
{
    std::vector<std::vector<int>> matrix = {
      {0, 0},
      {0, 0}
    };

    int size = matrix.size();
    int size_sqr = size * size;

    for (int i = 0; i < size_sqr; ++i) {
        int y = i / size;
        int x = i % size;
        matrix[y][x] = 1;
        measure("m2", matrix);
        matrix[y][x] = 0;
    }
}

int main() {
    m4();
    m2();
    return 0;
}
