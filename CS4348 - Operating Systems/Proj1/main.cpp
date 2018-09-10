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
#define NULL_ADDR   (-1)

int main() {

    pid_t m_pid = 0;
    cpu *cpu1 = nullptr;
    memory *memory1 = nullptr;

    int fd[2];

    printf("Hello World!\n");

    if (pipe(fd) == -1) {
        perror("Error Creating Pipe, exiting....");
        return 0;
    }

    m_pid = fork();
    int status;

    cpu1 = new cpu(fd);
    memory1 = new memory(fd);

    if (m_pid == 0) {
        printf("Parent\n");

        // while (things in stack):
        //  go to first item in stack
        //  access instruction and run function
        //      do anything you need to do in the function
        // update stack pointer

        std::string s;
        char ch;
        while (read(fd[READ_FD], &ch, 1) > 0)
        {
            if (ch != 0)
                s.push_back(ch);
            else
            {
                std::cout << s << '\n';
                break;
            }
        }

        if(s == "DONE") {
            printf("Memory Loaded!\n");
        }

    } else if (m_pid > 0) {
        // Load data Memory
        std::ifstream file("sample.txt");
        int tmp_stk_ptr = 2000 - 1;

        for (std::string line; getline(file, line);) {
            std::string value = line.substr(0, line.find(" "));
            memory1 -> _write(tmp_stk_ptr, value);
            tmp_stk_ptr--;
        }

        // Let the CPU know that the data has been loaded into memory
        std::string s = "DONE";
        write(fd[WRITE_FD], s.c_str(), s.length()+1);

        printf("Child\n");
        exit(1);
    } else {
        printf("Error");
        return 0;
    }

    while (wait(&status) > 0) {}

    return 0;
}
