#include "../solver/solver.h"
#include <string>
#include <vector>

void measure(BestResult(*f)(), const std::string& name);
void measure(BestResult(*f)(const std::vector<std::vector<int>>& matrix), const std::string& name, const std::vector<std::vector<int>>& matrix);
void measure(const std::string& name, const std::vector<std::vector<int>>& matrix);
void measure_int(int(*f)(), const std::string& name);
