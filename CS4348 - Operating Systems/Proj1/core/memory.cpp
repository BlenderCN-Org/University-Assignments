//
// Created by Matt on 9/8/2018.
//

#define READ_FD     0
#define WRITE_FD    1

#include "memory.h"

memory::memory(int *read, int *write) {
    read_pipe[READ_FD] = read[0];
    write_pipe[WRITE_FD] = write[1];
}

int memory::_read(int address) {
    write(write_pipe[WRITE_FD], &storage[address], sizeof(storage[address]));
}

void memory::_write(int address, int v) {
    storage[address] = v;
}

void memory::init() {
    alive = true;
    std::string s;
    while (alive) {
        s = read_from_pipe();
        std::istringstream iss(s);
        std::vector<std::string> tokens(std::istream_iterator<std::string>{iss},
                                        std::istream_iterator<std::string>());
        if (tokens.size() > 2) { // Save Operation
            int address = stoi(tokens.at(1));
            int v = stoi(tokens.at(2));
            _write(address, v);
        } else if (tokens.size() == 2) { // Load Operation
            int address = stoi(tokens.at(1));
            _read(address);
        }
    }
}

std::string memory::read_from_pipe() {
    std::string s;
    char ch;
    while (read(read_pipe[READ_FD], &ch, 1) > 0) {
        if (ch != 0)
            s.push_back(ch);
        else {
            std::cout << s << '\n';
            break;
        }
    }
    return s;
}
