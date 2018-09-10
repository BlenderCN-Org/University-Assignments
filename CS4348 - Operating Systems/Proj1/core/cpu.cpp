//
// Created by Matt on 9/8/2018.
//

#define READ_FD     0
#define WRITE_FD    1
#define NULL_ADDR   (-1)

#include "cpu.h"

cpu::cpu(int *pipe) {
    PC = 0;
    SP = 999;
    IR = 0;
    AC = 0;
    X = 0;
    Y = 0;

    fd[READ_FD] = pipe[READ_FD];
    fd[WRITE_FD] = pipe[WRITE_FD];
};

void cpu::init(int addr) {
    std::string test = "Test Message From Class";
    write(fd[WRITE_FD], test.c_str(), test.length() + 1);
    PC++;
}

void cpu::load_value(int value) {
    AC = value;
    PC++;
}

void cpu::load_addr(int addr) {
    std::string input_str = "G " + std::to_string(addr);
    write(fd[WRITE_FD], input_str.c_str(), input_str.length() + 1);

    std::string s = read_from_pipe(NULL_ADDR);

    char *n_chr;
    int value = strtol(s.c_str(), &n_chr, 10);
    AC = value;

    delete n_chr, value;
    PC++;
}

void cpu::load_indr_addr(int addr) {
    std::string input_str = "G " + std::to_string(addr);
    write(fd[WRITE_FD], input_str.c_str(), input_str.length() + 1);

    std::string s = read_from_pipe(NULL_ADDR);

    char *n_chr;
    int value = strtol(s.c_str(), &n_chr, 10);
    AC = value;

    delete n_chr, value;
    PC++;
}

void cpu::load_id_x(int addr) {
    std::string input_str = "G " + std::to_string(addr);
    write(fd[WRITE_FD], input_str.c_str(), input_str.length() + 1);

    std::string s = read_from_pipe(NULL_ADDR);

    char *n_chr;
    int value = strtol(s.c_str(), &n_chr, 10);
    value += X;
    AC = value;

    delete n_chr, value;
    PC++;
}

void cpu::load_id_y(int addr) {
    std::string input_str = "G " + std::to_string(addr);
    write(fd[WRITE_FD], input_str.c_str(), input_str.length() + 1);

    std::string s = read_from_pipe(NULL_ADDR);

    char *n_chr;
    int value = strtol(s.c_str(), &n_chr, 10);
    value += Y;
    AC = value;

    delete n_chr, value;
    PC++;
}

void cpu::load_sp_x(int addr) {
    AC += SP + X;
    PC++;
}

void cpu::store_addr(int addr) {
    std::string input_str = "G " + std::to_string(addr);
    write(fd[WRITE_FD], input_str.c_str(), input_str.length() + 1);
    PC++;
}

void cpu::get(int addr) {
    AC = rand() % 100 + 1;
    PC++;
}

void cpu::put_port(int type) {
    if (type == 1) {
        std::cout << AC << std::endl;
    } else if (type == 2) {
        std::cout << std::to_string(AC) << std::endl;
    } else {
        std::cout << "Invalid Instruction Type" << std::endl;
    }
    PC++;
}

void cpu::add_x(int addr) {
    AC += X;
    PC++;
}

void cpu::add_y(int addr) {
    AC += Y;
    PC++;
}

void cpu::sub_x(int addr) {
    AC -= X;
    PC++;
}

void cpu::sub_y(int addr) {
    AC -= Y;
    PC++;
}

void cpu::copy_to_x(int addr) {
    X = AC;
    PC++;
}

void cpu::copy_from_x(int addr) {
    AC = X;
    PC++;
}

void cpu::copy_to_y(int addr) {
    Y = AC;
    PC++;
}

void cpu::copy_from_y(int addr) {
    AC = Y;
    PC++;
}

void cpu::copy_to_sp(int addr) {
    SP = AC;
    PC++;
}

void cpu::copy_from_sp(int addr) {
    AC = SP;
    PC++;
}

void cpu::jump_addr(int addr) {
    SP = addr;
    PC++;
}

void cpu::jump_if_equal_addr(int addr) {
    if (AC == 0) {
        SP = addr;
        PC++;
    }
}

void cpu::jump_if_not_equal_addr(int addr) {
    if (AC != 0) {
        SP = addr;
        PC++;
    }
}

void cpu::call_addr(int addr) {
    std::string input_str = std::to_string(SP) + " " + std::to_string(SP);

    write(fd[WRITE_FD], input_str.c_str(), input_str.length() + 1);
    SP = addr;
    PC++;
}

void cpu::ret(int addr) {

}

void cpu::inc_x(int addr) {
    X++;
    PC++;
}

void cpu::inc_y(int addr) {
    Y++;
    PC++;
}

void cpu::push(int addr) {
    std::string input_str = std::to_string(SP) + " " + std::to_string(AC) + " 0";
    write(fd[WRITE_FD], input_str.c_str(), input_str.length() + 1);
    PC++;
}

void cpu::pop(int addr) {
    std::string input_str = std::to_string(SP) + " " + std::to_string(AC) + " 0";
    write(fd[WRITE_FD], input_str.c_str(), input_str.length() + 1);
    std::string s = read_from_pipe(NULL_ADDR);

    char *n_chr;
    AC = strtol(s.c_str(), &n_chr, 10);

    delete n_chr;
    PC++;
}

void cpu::syscall(int addr) {

}

void cpu::iret(int addr) {

}

void cpu::end(int addr) {
    std::cout << "Exit Instruction Called" << std::endl;
    exit(0);
}

std::string cpu::read_from_pipe(int addr) {
    std::string s;
    char ch;
    while (read(fd[READ_FD], &ch, 1) > 0) {
        if (ch != 0)
            s.push_back(ch);
        else {
            break;
        }
    }
    return s;
}

void cpu::write_to_pipe(std::string s) {
    write(fd[WRITE_FD], std::to_string(SP).c_str(), std::to_string(SP).length() + 1);
    write(fd[WRITE_FD], s.c_str(), s.length() + 1);
    SP--;
}
