set(SOURCES solve_array.cpp 
			solve_vector.cpp 
			solve_vector_simple.cpp
			solve_c_array.cpp 
			solve_c_array_old.cpp
			solve_c_flat.cpp			
			solve_permutation.cpp
			common.cpp)
set(HEADERS solver.h)

add_library(solverlib STATIC ${SOURCES} ${HEADERS})
add_executable(solverweb web/solve_web.cpp web/main.cpp)
add_executable(solverwebc web/solve_web.c web/main.c)
