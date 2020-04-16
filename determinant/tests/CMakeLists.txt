set(SOURCES tests.cpp)

add_executable(tests ${SOURCES})
set_property(TARGET tests PROPERTY CXX_STANDARD 20)

target_link_libraries(tests solverlib)
