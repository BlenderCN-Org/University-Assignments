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
#include <unistd.h>
#include <sys/types.h>

class memory {
public:
    explicit memory(int *, int *);

    int read_pipe[2];
    int write_pipe[2];

    int _read(int);

    void _write(int, int);

    void init();

private:
    int storage[2000] = {};
    bool alive = false;

    std::string read_from_pipe();
};


#endif //PROJ1_MEMORY_H
