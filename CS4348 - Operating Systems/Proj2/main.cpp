#include <iostream>

#include <cstdio>
#include <cstdlib>
#include <unistd.h>
#include <sys/types.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fstream>

#include "core/cpu.h"
#include "core/memory.h"

#define READ_FD     0
#define WRITE_FD    1

int main(int argc, char *argv[]) {

    // Variable setup, pid_ts, and the CPU and MEMORY object creation
    pid_t m_pid = 0;
    cpu *cpu1 = nullptr;
    memory *memory1 = nullptr;

    // Support variable setup, pipes and filenames
    int fd_1[2];
    int fd_2[2];
    int fd_3[2];
    char *filename = argv[1];

    // Throw an error on any pipe failures
    if (pipe(fd_1) == -1 || pipe(fd_2) == -1 || pipe(fd_3)) {
        perror("Error Creating Pipe, exiting....");
        return 0;
    }

    // Begin fork
    m_pid = fork();
    int status;


    // Initialize the CPU and MEMORY objects
    if (argc != 2) {
        cpu1 = new cpu(fd_1, fd_2, true, atoi(argv[2]));
    } else {
        cpu1 = new cpu(fd_1, fd_2, false, -1);
    }

    memory1 = new memory(fd_2, fd_1);

    // Parent process, the CPU
    if (m_pid == 0) {

        // Read from the pipe to see if the memory has finished loading
        std::string s;
        char ch;
        while (read(fd_3[READ_FD], &ch, 1) > 0) {
            if (ch != 0)
                s.push_back(ch);
            else {
                break;
            }
        }

        // Throw an error if there is any issue loading memory.
        if (s != "DONE") {
            std::cout << "Error when loading memory" << std::endl;
            exit(0);
        }

        // Begin the loop function, init() ; start the CPU
        cpu1->init();

    }
    // Child process, the MEMORY
    else if (m_pid > 0) {

        // Load data into Memory
        std::ifstream file(filename);
        int tmp_stk_ptr = 0;

        for (std::string line; getline(file, line);) {
            std::string value = line.substr(0, line.find(' '));
            if (value.find('.') != -1) {
                std::string address = value.substr(value.find('.') + 1);
                tmp_stk_ptr = stoi(address);
            } else if (value != "\n" && value != "\r" && value != "") {
                memory1->_write(tmp_stk_ptr, stoi(value));
                tmp_stk_ptr++;
            }
        }

        // Let the CPU know that the data has been loaded into memory
        std::string s = "DONE";
        write(fd_3[WRITE_FD], s.c_str(), s.length() + 1);

        // Begin the loop function, init() ; start the MEMORY
        memory1->init();

        exit(1);
    } else {
        printf("Error");
        return 0;
    }

    // Waits for all processes to finish before returning
    while (wait(&status) > 0) {}

    return 0;
}
