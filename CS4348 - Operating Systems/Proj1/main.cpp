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

    int interrupt_count;
    if(argc == 1) {
        interrupt_count = 1; // Fail here on final release
    } else {
        interrupt_count = atoi(argv[1]);
    }

    pid_t m_pid = 0;
    cpu *cpu1 = nullptr;
    memory *memory1 = nullptr;

    int fd_1[2];
    int fd_2[2];
    int fd_3[2];

    if (pipe(fd_1) == -1 || pipe(fd_2) == -1 || pipe(fd_3)) {
        perror("Error Creating Pipe, exiting....");
        return 0;
    }

    m_pid = fork();
    int status;

    cpu1 = new cpu(fd_1, fd_2);
    memory1 = new memory(fd_2, fd_1);

    if (m_pid == 0) {
        printf("Parent Begun\n");

        std::string s;
        char ch;
        while (read(fd_3[READ_FD], &ch, 1) > 0) {
            if (ch != 0)
                s.push_back(ch);
            else {
                std::cout << s << '\n';
                break;
            }
        }

        if (s == "DONE") {
            printf("Memory Loaded!\n");
        }

        cpu1->init(interrupt_count);

    } else if (m_pid > 0) {
        printf("Child Begun\n");

        // Load data Memory
        std::ifstream file("sample.txt");
        int tmp_stk_ptr = 0;

        for (std::string line; getline(file, line);) {
            std::string value = line.substr(0, line.find(' '));
            if (value.find('.') != -1) {
                std::string address = value.substr(value.find('.') + 1);
                tmp_stk_ptr = stoi(address);
            } else if (value != "\n" && value != "\r") {
                memory1->_write(tmp_stk_ptr, stoi(value));
                tmp_stk_ptr++;
            }
        }

        // Let the CPU know that the data has been loaded into memory
        std::string s = "DONE";
        write(fd_3[WRITE_FD], s.c_str(), s.length() + 1);

        memory1->init();

        exit(1);
    } else {
        printf("Error");
        return 0;
    }

    while (wait(&status) > 0) {}

    return 0;
}
