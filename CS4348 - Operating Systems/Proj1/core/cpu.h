//
// Created by Matt on 9/8/2018.
//

#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <unistd.h>
#include <sys/types.h>

#ifndef PROJ1_CPU_H
#define PROJ1_CPU_H


class cpu {
public:
    explicit cpu(int *);

    int fd[2];

    void init(int);

private:
    int PC;
    int SP;
    int IR;
    int AC;
    int X;
    int Y;

    using InstructionSet = void (cpu::*)(int);
    InstructionSet instructions[31] = {
            &cpu::load_value,
            &cpu::load_addr,
            &cpu::load_indr_addr,
            &cpu::load_id_x,
            &cpu::load_id_y,
            &cpu::load_sp_x,
            &cpu::store_addr,
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
            &cpu:: copy_from_sp,
            &cpu::jump_addr,
            &cpu:: jump_if_equal_addr,
            &cpu:: jump_if_not_equal_addr,
            &cpu::call_addr,
            &cpu:: ret,
            &cpu:: inc_x,
            &cpu:: inc_y,
            &cpu:: push,
            &cpu::  pop,
            &cpu:: syscall,
            &cpu::iret,
            &cpu::end
    };

    void load_value(int);

    void load_addr(int);

    void load_indr_addr(int);

    void load_id_x(int);

    void load_id_y(int);

    void load_sp_x(int);

    void store_addr(int);

    void get(int);

    void put_port(int);

    void add_x(int);

    void add_y(int);

    void sub_x(int);

    void sub_y(int);

    void copy_to_x(int);

    void copy_from_x(int);

    void copy_to_y(int);

    void copy_from_y(int);

    void copy_to_sp(int);

    void copy_from_sp(int);

    void jump_addr(int);

    void jump_if_equal_addr(int);

    void jump_if_not_equal_addr(int);

    void call_addr(int);

    void ret(int);

    void inc_x(int);

    void inc_y(int);

    void push(int);

    void pop(int);

    void syscall(int);

    void iret(int);

    void end(int);

    std::string read_from_pipe(int);

    void write_to_pipe(std::string);

};


#endif //PROJ1_CPU_H
