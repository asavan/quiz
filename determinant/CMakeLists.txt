cmake_minimum_required(VERSION 3.1)

# SET(CMAKE_CXX_FLAGS "-std=c++0x -Wall -Wextra -pedantic")

project(determinant)

add_subdirectory(solver)
add_subdirectory(tests)

set(SOURCES main.cpp)

add_executable(determinant ${SOURCES})
set_property(TARGET determinant PROPERTY CXX_STANDARD 20)

target_link_libraries(determinant solverlib)
