cmake_minimum_required(VERSION 3.10)
project(Proj1)

set(CMAKE_CXX_STANDARD 11)

add_executable(Proj1 main.cpp core/memory.cpp core/memory.h core/cpu.cpp core/cpu.h)