//
// Created by Matt on 9/8/2018.
//

#define READ_FD     0
#define WRITE_FD    1

#include "memory.h"

// Constructor:
//  Takes in a read pipe, wrire pipe
memory::memory(int *read, int *write) {
    read_pipe = read;
    write_pipe = write;
}

// Reads a value and stores it in the memory array at
// location 'address'
int memory::_read(int address) {
    int v = storage[address];
    write(write_pipe[WRITE_FD], &v, sizeof(v));
}

// Save a value to the array
void memory::_write(int address, int v) {
    storage[address] = v;
}

// Reads from the pipe hwile alive, tokenizes it to determine
// the type of operation neeeded, and performs the correct
void memory::init() {
    alive = true;
    std::string s;
    while (alive) {
        s = read_from_pipe();
        std::istringstream iss(s);
        std::vector<std::string> tokens(std::istream_iterator<std::string>{iss},
                                        std::istream_iterator<std::string>());
        if (s == "EXIT") {
            exit(0);
        } else if (tokens.size() > 1) { // Save Operation
            int address = stoi(tokens.at(0));
            int v = stoi(tokens.at(1));
            _write(address, v);
        } else if (tokens.size() == 1) { // Load Operation
            int address = stoi(tokens.at(0));
            _read(address);
        }
    }
}

// Reads a value from the array
std::string memory::read_from_pipe() {
    std::string s;
    char ch;
    while (read(read_pipe[READ_FD], &ch, 1) > 0) {
        if (ch != 0)
            s.push_back(ch);
        else {
            break;
        }
    }
    return s;
}
