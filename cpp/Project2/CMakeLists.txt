cmake_minimum_required(VERSION 3.6)

project(window)

file(GLOB window_SRC
    "*.h"
    "*.cpp"
)

add_executable(window ${window_SRC})
set_property(DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR} PROPERTY VS_STARTUP_PROJECT window)
