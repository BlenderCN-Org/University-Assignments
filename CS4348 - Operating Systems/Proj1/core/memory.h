//
// Created by Matt on 9/8/2018.
//

#ifndef PROJ1_MEMORY_H
#define PROJ1_MEMORY_H

#include <iostream>
#include <string>
#include <stdio.h>
#include <cstdlib>
#include <unistd.h>
#include <sys/types.h>

class memory {
public:
    memory(int *);

    int fd[2];

    int _read(int);

    void _write(int, std::string);

private:
    std::string storage[2000] = {};

    void init();
};


#endif //PROJ1_MEMORY_H
