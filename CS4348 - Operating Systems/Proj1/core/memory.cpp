//
// Created by Matt on 9/8/2018.
//

#define READ_FD     0
#define WRITE_FD    1

#include "memory.h"

memory::memory(int pipe[2]) {
    fd[READ_FD] = pipe[0];
    fd[WRITE_FD] = pipe[1];
}

int memory::_read(int addr) {
    write(fd[WRITE_FD], storage[addr].c_str(), storage[addr].length() + 1);
}

void memory::_write(int address, std::string data) {
    storage[address] = data;
}

void memory::init() {

}
