set(SOURCES solve_array.cpp 
			solve_vector.cpp 
			solve_c_array.cpp 
			solve_c_array_old.cpp 
			solve_permutation.cpp
			common.cpp)
set(HEADERS solver.h)

add_library(solverlib STATIC ${SOURCES} ${HEADERS})
