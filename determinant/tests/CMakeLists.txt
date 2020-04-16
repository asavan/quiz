set(SOURCES tests.cpp)

add_executable(tests ${SOURCES})
set_property(TARGET tests PROPERTY CXX_STANDARD 20)

add_executable(time_measure time_measure.cpp)

target_link_libraries(tests solverlib)
target_link_libraries(time_measure solverlib)
