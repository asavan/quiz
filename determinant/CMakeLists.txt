cmake_minimum_required(VERSION 3.1)

# SET(CMAKE_CXX_FLAGS "-std=c++0x -Wall -Wextra -pedantic")

project(determinant)

set(SOURCES main.cpp solve_array.cpp 
			solve_vector.cpp 
			solve_c_array.cpp 
			solve_permutation.cpp
			common.cpp)
set(HEADERS solver.h)

add_executable(determinant ${SOURCES} ${HEADERS})
set_property(TARGET determinant PROPERTY CXX_STANDARD 20)

# target_link_libraries(determinant solverlib)
