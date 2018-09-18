//
// Created by Matt on 9/8/2018.
//

#define READ_FD     0
#define WRITE_FD    1

#include "cpu.h"

cpu::cpu(int *read, int *write) {
    PC = 0;
    SP = 0;
    IR = 0;
    AC = 0;
    X = 0;
    Y = 0;

    read_pipe = read;
    write_pipe = write;
};

void cpu::init(int interrupt_count) {
    alive = true;
    while (alive) {
        write_to_pipe(PC++);
        IR = read_from_pipe();
        execute_instruction();

        if(PC % interrupt_count == 0) {
            // Do Interrupt...
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
    write_to_pipe(SP + X);
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
        printf("%i\n", AC);
    } else if (v == 2) {
        printf("%c\n", (char) AC);
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

    PC = v;
}

void cpu::jump_if_equal_address() {
    if (AC == 0) {
        jump_address();
    }
}

void cpu::jump_if_not_equal_address() {
    if (AC != 0) {
        jump_address();
    }
}

void cpu::call_address() {
//    std::cout << "Called Address Function!" << std::endl;
}

void cpu::ret() {

}

void cpu::inc_x() {
    X++;
}

void cpu::inc_y() {
    Y++;
}

void cpu::push() {

}

void cpu::pop() {

}

void cpu::syscall() {

}

void cpu::iret() {

}

void cpu::handle_interrupt(int t) {

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
//    std::cout << "Parent: Wrote: " << a << "..." << std::endl;
    std::ostringstream stream;
    stream << a;
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
