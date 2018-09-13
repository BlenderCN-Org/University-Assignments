//
// Created by Matt on 9/8/2018.
//

#include <iostream>
#include <string>
#include <sstream>
#include <random>
#include <cstdio>
#include <cstdlib>
#include <unistd.h>
#include <sys/types.h>

#ifndef PROJ1_CPU_H
#define PROJ1_CPU_H


class cpu {
public:
    explicit cpu(int *, int *);

    int read_pipe[2];
    int write_pipe[2];

    void init();

    void execute_instruction(int);

private:
    int PC;
    int SP;
    int IR;
    int AC;
    int X;
    int Y;

    bool alive = false;

    void load_value();

    void load_address();

    void load_indr_address();

    void load_id_x();

    void load_id_y();

    void load_sp_x();

    void store_address();

    void get();

    void put_port();

    void add_x();

    void add_y();

    void sub_x();

    void sub_y();

    void copy_to_x();

    void copy_from_x();

    void copy_to_y();

    void copy_from_y();

    void copy_to_sp();

    void copy_from_sp();

    void jump_address();

    void jump_if_equal_address();

    void jump_if_not_equal_address();

    void call_address();

    void ret();

    void inc_x();

    void inc_y();

    void push();

    void pop();

    void syscall();

    void iret();

    void end();

    int read_from_pipe();

    void write_to_pipe(int);

    void write_to_pipe(int, int);

    typedef void (cpu::*InstructionSet)();

    InstructionSet instructions[31] = {
            &cpu::load_value,
            &cpu::load_address,
            &cpu::load_indr_address,
            &cpu::load_id_x,
            &cpu::load_id_y,
            &cpu::load_sp_x,
            &cpu::store_address,
            &cpu::get,
            &cpu::put_port,
            &cpu::add_x,
            &cpu::add_y,
            &cpu::sub_x,
            &cpu::sub_y,
            &cpu::copy_to_x,
            &cpu::copy_from_x,
            &cpu::copy_to_y,
            &cpu::copy_from_y,
            &cpu::copy_to_sp,
            &cpu::copy_from_sp,
            &cpu::jump_address,
            &cpu::jump_if_equal_address,
            &cpu::jump_if_not_equal_address,
            &cpu::call_address,
            &cpu::ret,
            &cpu::inc_x,
            &cpu::inc_y,
            &cpu::push,
            &cpu::pop,
            &cpu::syscall,
            &cpu::iret,
            &cpu::end
    };

};


#endif //PROJ1_CPU_H
