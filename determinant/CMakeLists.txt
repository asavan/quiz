cmake_minimum_required(VERSION 2.8)

# SET(CMAKE_CXX_FLAGS "-std=c++0x -Wall -Wextra -pedantic")

project(determinant)

set(SOURCES main.cpp solve_array.cpp solve_vector.cpp solve_c_array.cpp solve_permutation.cpp)

add_executable(determinant ${SOURCES})
set_property(TARGET determinant PROPERTY CXX_STANDARD 20)

# target_link_libraries(letter_puzzle solverlib puzzleslib)
