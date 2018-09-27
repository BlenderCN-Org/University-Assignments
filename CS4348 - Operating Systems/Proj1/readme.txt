- To compile on CS1 while in the project directory: g++ main.cpp core/cpu.cpp core/memory.cpp -std=c++11

- Launch Instructions: ./a.out [filename] [timer instruction amount (if any)]

- For the sample5.txt ( the user created script ), use and timer value > 4. The script will
get a random number from 1 to 100 and will subtract increasingly more values (x++) from
that number until it hits 0, in which it will end the program. Differently timer values
result in different results. For example, when you run the timer, it will load a random
value into the AC, thus making it spastic. Also note that values are supposed to be
spaced out line by liner, but the timer could also influence this behaviour and numbers
may not all be on seperate lines.