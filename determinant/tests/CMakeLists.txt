add_executable(tests tests.cpp)
add_executable(time_measure time_measure.cpp)

target_link_libraries(tests solverlib)
target_link_libraries(time_measure solverlib measurelib)
