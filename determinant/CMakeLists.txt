cmake_minimum_required(VERSION 3.18)

# SET(CMAKE_CXX_FLAGS "-std=c++0x -Wall -Wextra -pedantic")


project(determinant)

set(CMAKE_DISABLE_PRECOMPILE_HEADERS ON)

add_subdirectory(solver)
add_subdirectory(measure)
add_subdirectory(tests)

set(SOURCES main.cpp)

add_executable(determinant ${SOURCES})
set_property(TARGET determinant PROPERTY CXX_STANDARD 20)
set_property(DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR} PROPERTY VS_STARTUP_PROJECT determinant)

target_link_libraries(determinant solverlib measurelib)
