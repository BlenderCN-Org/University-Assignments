//
// Created by Matt on 9/8/2018.
//

#ifndef PROJ1_MEMORY_H
#define PROJ1_MEMORY_H

#include <iostream>
#include <string>
#include <iterator>
#include <vector>
#include <sstream>
#include <cstdio>
#include <cstdlib>
#include <thread>
#include <chrono>
#include <unistd.h>
#include <sys/types.h>

class memory {
public:
    // Constructor
    explicit memory(int *, int *);

    // Pipes
    int *read_pipe;
    int *write_pipe;

    // Writes an value from a specified address
    int _read(int);

    // Saves a value to memory
    void _write(int, int);

    // Function that runs the MEMORY
    void init();

private:
    // Array that stores data
    int storage[2000] = {};

    // If the MEMORY is alive
    bool alive = false;

    // Helper function that reads from pipe
    std::string read_from_pipe();
};


#endif //PROJ1_MEMORY_H
