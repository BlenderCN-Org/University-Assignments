//
// Created by Matt on 9/8/2018.
//

#include <iostream>
#include <string>
#include <sstream>
#include <random>
#include <thread>
#include <chrono>
#include <cstring>
#include <cstdio>
#include <cstdlib>
#include <unistd.h>
#include <sys/types.h>

#ifndef PROJ1_CPU_H
#define PROJ1_CPU_H


class cpu {
public:
    // Constructor
    explicit cpu(int *, int *, bool, int);

    // Pipes
    int *read_pipe;
    int *write_pipe;

    // Function that runs the CPU
    void init();

    // Interprets the IR and executes a function based on its params
    void execute_instruction();

private:
    // Register Setup
    int PC;
    int SP;
    int IR;
    int AC;
    int X;
    int Y;

    // Variables for the loop, modes, and scheduler
    bool alive;
    bool kernel_mode;
    bool scheduler;
    int instruction_counter;
    int timer;

    // instruction 1
    void load_value();

    // instruction 2
    void load_address();

    // instruction 3
    void load_indr_address();

    // instruction 4
    void load_id_x();

    // instruction 5
    void load_id_y();

    // instruction 6
    void load_sp_x();

    // instruction 7
    void store_address();

    // instruction 8
    void get();

    // instruction 9
    void put_port();

    // instruction 10
    void add_x();

    // instruction 11
    void add_y();

    // instruction 12
    void sub_x();

    // instruction 13
    void sub_y();

    // instruction 14
    void copy_to_x();

    // instruction 15
    void copy_from_x();

    // instruction 16
    void copy_to_y();

    // instruction 17
    void copy_from_y();

    // instruction 18
    void copy_to_sp();

    // instruction 19
    void copy_from_sp();

    // instruction 20
    void jump_address();

    // instruction 21
    void jump_if_equal_address();

    // instruction 22
    void jump_if_not_equal_address();

    // instruction 23
    void call_address();

    // instruction 24
    void ret();

    // instruction 25
    void inc_x();

    // instruction 26
    void dec_x();

    // instruction 27
    void push();

    // instruction 28
    void pop();

    // instruction 29
    void syscall();

    // Helper function for the timer syscall
    void syscall_timer();

    // instruction 30
    void iret();

    // instruction 50
    void end();

    // Helper function that reads values from the pipe
    int read_from_pipe();

    // Helper function that writes an address to the pipe for LOADing
    // operation
    void write_to_pipe(int);

    // Helper function that writes and address and a value to the pipe for
    // a SAVEing operation
    void write_to_pipe(int, int);

    // Helper function that writes EXIT to the pipe, signaling for
    // the memory to also exit
    void write_to_pipe();

    // Array of function pointers, cooler than your typical ol'
    // switch statement. Works in the same way except for a special
    // case for the exit function
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
            &cpu::dec_x,
            &cpu::push,
            &cpu::pop,
            &cpu::syscall,
            &cpu::iret,
            &cpu::end
    };

};


#endif //PROJ1_CPU_H
