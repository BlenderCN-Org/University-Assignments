//
// Created by Matt on 9/8/2018.
//

#define READ_FD     0
#define WRITE_FD    1

#include "cpu.h"

// Constructor:
//  Takes in a read pipe, wrire pipe, whether the
//  scheduler should be enabled, and the amount
//  of instructions it should interrupt on, as well
//  as variable creation
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


// Init function that loops while the CPU is alive
//  Writes to the pipe
//  Reads and stores value in the instruction register
//  Executes an instruction based on the IR
//  Performs scheduler handling
void cpu::init() {
    alive = true;
    while (alive) {
        write_to_pipe(PC++);
        IR = read_from_pipe();
        execute_instruction();

        if (scheduler && !kernel_mode && instruction_counter > 0 && instruction_counter == timer) {
            syscall_timer();
            instruction_counter++;
            instruction_counter = 0;
        }
        if (scheduler && !kernel_mode) {
            instruction_counter++;
        }
    }
}

// Executes an instruction by calling its position in
// the function array
void cpu::execute_instruction() {
    if (IR == 50) {
        IR = 31;
    }
    (this->*instructions[IR - 1])();
}

// Loads a value into the AC
void cpu::load_value() {
    write_to_pipe(PC++);
    int v = read_from_pipe();
    AC = v;
}

// Loads a value from the address into the AC
void cpu::load_address() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    write_to_pipe(v);
    v = read_from_pipe();
    AC = v;
}

// Load value from address's value into the AC
void cpu::load_indr_address() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    write_to_pipe(v);
    v = read_from_pipe();

    write_to_pipe(v);
    v = read_from_pipe();

    AC = v;
}

// Load a value at address + x into the AC
void cpu::load_id_x() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    write_to_pipe(v + X);
    v = read_from_pipe();

    AC = v;
}

// Load a value at address + y into the AC
void cpu::load_id_y() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    write_to_pipe(v + Y);
    v = read_from_pipe();

    AC = v;
}

// Load a value from the stack pointer + x into the AC
void cpu::load_sp_x() {
    write_to_pipe(SP + 1 + X);
    int v = read_from_pipe();

    AC = v;
}

// Store a value into the address
void cpu::store_address() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    write_to_pipe(v, AC);
}

// Gets a random value
void cpu::get() {
    AC = rand() % 100 + 1;
}

// Prints the AC to the screem based off of certain params
void cpu::put_port() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    if (v == 1) {
        printf("%i", AC);
    } else if (v == 2) {
        printf("%c", (char) AC);
    }
}

// Add X to the AC
void cpu::add_x() {
    AC += X;
}

// Add Y to the AC
void cpu::add_y() {
    AC += Y;
}

// Subrtact X from the AC
void cpu::sub_x() {
    AC -= X;
}

// Subtract Y from the AC
void cpu::sub_y() {
    AC -= Y;
}

// Copy the AC to X
void cpu::copy_to_x() {
    X = AC;
}

// Copy X to the AC
void cpu::copy_from_x() {
    AC = X;
}

// Copy Y to the AC
void cpu::copy_to_y() {
    Y = AC;
}

// Copy the AC to Y
void cpu::copy_from_y() {
    AC = Y;
}

// Copy the AC to the SP
void cpu::copy_to_sp() {
    SP = AC;
}

// Copy the SP to the AC
void cpu::copy_from_sp() {
    AC = SP;
}

// Jump to a specific address
void cpu::jump_address() {
    write_to_pipe(PC++);
    int v = read_from_pipe();

    if (!kernel_mode && v > 999) {
        printf("Memory violation: accessing system address > 1000 (int array 999) in user mode\n");
        end();
    }

    PC = v;
}

// Jump to an address if AC == 0
void cpu::jump_if_equal_address() {
    if (AC == 0) {
        jump_address();
    } else {
        PC++;
    }
}

// Jump to address if AC != 0
void cpu::jump_if_not_equal_address() {
    if (AC != 0) {
        jump_address();
    } else {
        PC++;
    }
}

// Write the PC to the stack
void cpu::call_address() {
    write_to_pipe(SP--, (PC + 1));
    jump_address();
}

// Return from the stack
void cpu::ret() {
    SP++;
    write_to_pipe(SP);
    int v = read_from_pipe();

    PC = v;
}

// Increase X by 1 value
void cpu::inc_x() {
    X++;
}

// Decrease X by 1 value
void cpu::dec_x() {
    X--;
}

// Push AC onto the stack
void cpu::push() {
    write_to_pipe(SP--, AC);
}

// Pop value from the stack
void cpu::pop() {
    SP++;
    write_to_pipe(SP);
    AC = read_from_pipe();
}

// System call handler
void cpu::syscall() {
    int system_stack_address = 1999;
    write_to_pipe(system_stack_address--, SP);
    write_to_pipe(system_stack_address--, PC);
    SP = system_stack_address;
    PC = 1500;
    kernel_mode = true;
}

// Interrupt system call
void cpu::syscall_timer() {
    int system_stack_address = 1999;
    write_to_pipe(system_stack_address--, SP);
    write_to_pipe(system_stack_address--, PC);
    SP = system_stack_address;
    PC = 1000;
    kernel_mode = true;
}

// Return from syscall
void cpu::iret() {
    SP++;
    write_to_pipe(SP);
    PC = read_from_pipe();

    SP++;
    write_to_pipe(SP);
    SP = read_from_pipe();
    kernel_mode = false;
}

// End the program
void cpu::end() {
    write_to_pipe();
    alive = false;
    exit(0);
}

// Helper function to read data from the pipe
int cpu::read_from_pipe() {
    int v = 0;
    read(read_pipe[READ_FD], &v, sizeof(v));
    return v;
}

// Helper function to write and receive address
void cpu::write_to_pipe(int a) {
    std::ostringstream stream;
    stream << a;
    if (!kernel_mode && a > 999) {
        printf("Memory violation: accessing system address > 1000 (int array 999) in user mode\n");
        end();
    }
    write(write_pipe[WRITE_FD], stream.str().c_str(), stream.str().length() + 1);
}

// Helper write function that saves a value to the address
void cpu::write_to_pipe(int a, int v) {
    std::ostringstream stream;
    stream << a << " " << v;
    write(write_pipe[WRITE_FD], stream.str().c_str(), stream.str().length() + 1);
}

// Helper function to send an end signal
void cpu::write_to_pipe() {
    std::ostringstream stream;
    stream << "EXIT";
    write(write_pipe[WRITE_FD], stream.str().c_str(), stream.str().length() + 1);
}
