//
// Created by Matt on 9/8/2018.
//

#define READ_FD     0
#define WRITE_FD    1

#include "cpu.h"

cpu::cpu(int *read, int *write, bool s, int t) {
    PC = 0;
    SP = 999;
    IR = 0;
    AC = 0;
    X = 0;
    Y = 0;
    alive = false;
    kernel_mode = false;
    scheduler = s;
    timer = t;
    instruction_counter = 0;

    read_pipe = read;
    write_pipe = write;
}

void cpu::init() {
    alive = true;
    while (alive) {
//        std::cout << "Running CPU" << std::endl;
        write_to_pipe(PC++);
        IR = read_from_pipe();
        execute_instruction();

        if (scheduler && !kernel_mode && instruction_counter > 0 && (instruction_counter % timer == 0)) {
//            std::cout << "timer interrupt" << std::endl;
            syscall_timer();
            instruction_counter++;
        }
        if(scheduler && !kernel_mode) {
            instruction_counter++;
        }
    }
}

void cpu::execute_instruction() {
    if (IR == 50) {
        IR = 31;
    }
    (this->*instructions[IR - 1])();
}

void cpu::load_value() {
    write_to_pipe(PC++);
    int v = read_from_pipe();
    AC = v;
}

void cpu::load_address() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    write_to_pipe(v);
    v = read_from_pipe();
    AC = v;
}

void cpu::load_indr_address() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    write_to_pipe(v);
    v = read_from_pipe();

    write_to_pipe(v);
    v = read_from_pipe();

    AC = v;
}

void cpu::load_id_x() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    write_to_pipe(v + X);
    v = read_from_pipe();

    AC = v;
}

void cpu::load_id_y() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    write_to_pipe(v + Y);
    v = read_from_pipe();

    AC = v;
}

void cpu::load_sp_x() {
    write_to_pipe(SP + 1 + X);
    int v = read_from_pipe();

    AC = v;
}

void cpu::store_address() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    write_to_pipe(v, AC);
}

void cpu::get() {
    AC = rand() % 100 + 1;
}

void cpu::put_port() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    if (v == 1) {
        printf("%i", AC);
    } else if (v == 2) {
        printf("%c", (char) AC);
    }
}

void cpu::add_x() {
    AC += X;
}

void cpu::add_y() {
    AC += Y;
}

void cpu::sub_x() {
    AC -= X;
}

void cpu::sub_y() {
    AC -= Y;
}

void cpu::copy_to_x() {
    X = AC;
}

void cpu::copy_from_x() {
    AC = X;
}

void cpu::copy_to_y() {
    Y = AC;
}

void cpu::copy_from_y() {
    AC = Y;
}

void cpu::copy_to_sp() {
    SP = AC;
}

void cpu::copy_from_sp() {
    AC = SP;
}

void cpu::jump_address() {
    write_to_pipe(PC++);
    int v = read_from_pipe();
//    std::cout << "Jumping to: " << v << std::endl;

    PC = v;
}

void cpu::jump_if_equal_address() {
    if (AC == 0) {
        jump_address();
    } else {
        PC++;
    }
}

void cpu::jump_if_not_equal_address() {
    if (AC != 0) {
        jump_address();
    } else {
        PC++;
    }
}

void cpu::call_address() {
    write_to_pipe(SP--, (PC + 1));
    jump_address();
}

void cpu::ret() {
    SP++;
    write_to_pipe(SP);
    int v = read_from_pipe();
//    std::cout << "Returning to: " << v << std::endl;

    PC = v;
}

void cpu::inc_x() {
    X++;
//    std::cout << "X: " << X << std::endl;
}

void cpu::dec_x() {
    X--;
//    std::cout << "X: " << X << std::endl;
}

void cpu::push() {
    write_to_pipe(SP--, AC);
}

void cpu::pop() {
    SP++;
    write_to_pipe(SP);
    AC = read_from_pipe();
}

void cpu::syscall() {
    int system_stack_address = 1999;
    write_to_pipe(system_stack_address--, SP);
    write_to_pipe(system_stack_address--, PC);
    SP = system_stack_address;
    PC = 1500;
    kernel_mode = true;
}

void cpu::syscall_timer() {
//    std::cout << "Calling from Timer!" << std::endl;
    int system_stack_address = 1999;
    write_to_pipe(system_stack_address--, SP);
    write_to_pipe(system_stack_address--, PC);
    SP = system_stack_address;
    PC = 1000;
    kernel_mode = true;
}

void cpu::iret() {
//    std::cout << "End Interrupt!" << std::endl;
    SP++;
    write_to_pipe(SP);
    PC = read_from_pipe();

    SP++;
    write_to_pipe(SP);
    SP = read_from_pipe();
    kernel_mode = false;
}

void cpu::end() {
//    std::cout << "Exit Instruction Called" << std::endl;
    write_to_pipe();
    alive = false;
    exit(0);
}

int cpu::read_from_pipe() {
//    std::cout << "Parent: Attempting to read from pipe..." << std::endl;
    int v = 0;
    read(read_pipe[READ_FD], &v, sizeof(v));
//    std::cout << "Parent: Read: " << v << std::endl;
    return v;
}

void cpu::write_to_pipe(int a) {
//    std::cout << "Parent: Wrote: " << a << std::endl;
    std::ostringstream stream;
    stream << a;
    if (!kernel_mode && a > 999) {
        printf("Memory violation: accessing system address > 1000 (int array 999) in user mode\n");
        end();
    }
    write(write_pipe[WRITE_FD], stream.str().c_str(), stream.str().length() + 1);
}

void cpu::write_to_pipe(int a, int v) {
    std::ostringstream stream;
    stream << a << " " << v;
    write(write_pipe[WRITE_FD], stream.str().c_str(), stream.str().length() + 1);
}

void cpu::write_to_pipe() {
    std::ostringstream stream;
    stream << "EXIT";
    write(write_pipe[WRITE_FD], stream.str().c_str(), stream.str().length() + 1);
}
