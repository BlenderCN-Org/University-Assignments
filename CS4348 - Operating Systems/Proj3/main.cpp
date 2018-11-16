#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <string>
#include <bitset>
#include <math.h>
#include <fstream>
#include <vector>
#include <iterator>
#include <sstream>
#include <cstring>
#include <algorithm>

using namespace std;

class _disk {

private:

    struct file_row {
        string file_name;
        int file_index;
        int file_span;

        bool operator<(const file_row &a) const {
            return file_index < a.file_index;
        }

    };

    /*
     * First block is for file allocation table
     * Second block is for a bitmap of free space management
     */
    bitset<4096> memory[256];

    /* 0 = contiguous, 1 = chained, 2 = indexed */
    int allocation_method;

    /*
     * Max file size in bits
     * Note an ASCII character is 8bits (1 byte)
     */
    int max_file_size = 10 * 512 * 4;

    void add_data(int block_index, string binary, string filename) {

        auto offset = static_cast<int>(ceil(binary.length() / 512.0));
        string temp;
        int size = binary.length() * 8;

        for (int i = block_index; i < block_index + offset; i++) {
            int buffer_size;
            if (size >= 512) {
                buffer_size = 512 / 8;
            } else {
                buffer_size = size / 8;
            }
            for (int j = 0; j < buffer_size; j++) {
                temp += binary.at((i - block_index) * 512 + j);
            }
            memory[i] = append(memory[i], temp);
            memory[0] = append(memory[0], filename + " " + to_string(block_index) + " " + to_string(size), 1);
            // Special increment to append (forward) to the free space table?
            size -= 512;
        }
    }


    bitset<4096> append(bitset<4096> set, string s, int increments = 0) {
        bitset<4096> appended_set;

        if (increments == 0) {
            for (char &c : s) {
                bitset<4096> _s(static_cast<unsigned long long int>(c));

                int offset = sizeof(c) * 8;
                appended_set = shift(set, offset);

                for (int i = 0; i < offset; i++) {
                    appended_set[i] = _s[i];
                }
                set = appended_set;
            }
        } else if (increments == 1) {

            std::istringstream iss(s);
            std::vector<std::string> tokens((std::istream_iterator<std::string>(iss)),
                                            std::istream_iterator<std::string>());

            for (int i = 0; i < tokens.size(); i++) {
                for (char &c : tokens.at(static_cast<unsigned int>(i))) {
                    bitset<4096> _s(static_cast<unsigned long long int>(c));

                    int offset = sizeof(c) * 8;
                    appended_set = shift(set, offset);

                    for (int j = 0; j < offset; j++) {
                        appended_set[j] = _s[j];
                    }
                    set = appended_set;
                }

                switch (i) {
                    case 0:
                        appended_set = shift(appended_set, 8);
                        break;
                    case 1:
                        appended_set = shift(appended_set, 3);
                        break;
                    default: ;

                }

            }
        }

        return appended_set;
    }

    bitset<4096> shift(bitset<4096> set, int offset) {
        bitset<4096> shifted_set;

        for (int i = 0 + offset; i < 4096; i++) {
            shifted_set[i] = set[i - offset];
        }
        return shifted_set;
    }

    bitset<4096> create_file_allocation_table() {
        bitset<4096> file_allocation_table;

        file_allocation_table = append(file_allocation_table, "filetabl 0 1", 1);
        file_allocation_table = append(file_allocation_table, "freetabl 1 1", 1);

        return file_allocation_table;
    }

    bitset<4096> create_free_space_table() {
        bitset<4096> free_space_table;

        for (int i = 0; i < 2 * 8; i++) {
            free_space_table[i] = 1;
        }

        return free_space_table;
    }

    int _write(int file_size, string binary, string filename) {

        vector<file_row> rows = get_file_allocation_rows();
        sort(rows.begin(), rows.end());

        int block_span = static_cast<int>(ceil(file_size / 512.0));

        int max = 0;
        int max_index = 0;
        int temp = 0;
        int temp_index = 0;
        switch (allocation_method) {
            case 0:
                int disp[256];
                fill(begin(disp), end(disp), 0);

                for (file_row &row : rows) {
                    for (int i = 0; i < row.file_span; i++) {
                        disp[row.file_index + i] = 1;
                    }
                }

                for (int i = 0; i < 256; i++) {
                    if (disp[i] == 0) {
                        temp++;
                    } else {
                        if (max < temp) {
                            max = temp;
                            max_index = temp_index;
                        }
                        temp = 0;
                        temp_index = i + 1;
                    }
                }
                if (max < temp) {
                    max = temp;
                    max_index = temp_index;
                }

                cout << "max index: " << max_index << ", with " << max << " free spaces" << endl;
                cout << "block_span target: " << block_span << endl;

                if (max >= block_span) {
                    add_data(max_index, binary, filename);
                }
                break;

            default: ;
        }
    }

    vector<file_row> get_file_allocation_rows() {

        bitset<4096> file_allocation_table = memory[0];
        vector<file_row> rows;

        int start = 0;
        for (int i = 0; i < 41; i++) {

            file_row row;

            string file_name;
            string file_index;
            string file_span;

            if (i > 0) {
                start = start + 80;
            }

            for (int s = start; s < start + 8; s++) {
                file_span = to_string(file_allocation_table[s]) + file_span;
            }

            for (int l = start + 8; l < start + 8 + 8; l++) {
                file_index = to_string(file_allocation_table[l]) + file_index;
            }

            for (int n = start + 8 + 8; n < start + 80; n++) {
                file_name = to_string(file_allocation_table[n]) + file_name;
            }

            if (file_name.find('1') == -1) {
                break;
            }

            string s_file_name;
            string s_file_index;
            string s_file_span;

            std::istringstream in(file_name);
            std::bitset<8> bs;
            while (in >> bs)
                s_file_name += char(bs.to_ulong());

            std::istringstream in2(file_index);
            std::bitset<8> bs2;
            while (in2 >> bs2)
                s_file_index += char(bs2.to_ulong());

            std::istringstream in3(file_span);
            std::bitset<8> bs3;
            while (in3 >> bs3)
                s_file_span += char(bs3.to_ulong());

            cout << s_file_name << ", " << s_file_index << ", " << s_file_span << endl;
            char *buffer;
            row.file_name = s_file_name;
            row.file_index = strtol(s_file_index.c_str(), &buffer, 10);
            row.file_span = strtol(s_file_span.c_str(), &buffer, 10);
            rows.push_back(row);
        }
        return rows;
    }


public:
    explicit _disk(int allocation_type) {
        cout << "Disk Initializing" << endl;

        allocation_method = allocation_type;
        cout << "\tAllocation Method:" << endl;
        switch (allocation_method) {
            case 0:
                cout << "\t\t- Contiguous allocation selected" << endl;
                break;
            case 1:
                cout << "\t\t- Chained allocation selected" << endl;
                break;
            case 2:
                cout << "\t\t- Indexed allocation selected" << endl;
                break;
            default: ;
        }

        for (auto &i : memory) {
            bitset<4096> block;
            i = block;
        }

        memory[0] = create_file_allocation_table();
        memory[1] = create_free_space_table();

        cout << "\tSpace Specifications:" << endl;
        cout << "\t\t- 256 blocks" << endl;
        cout << "\t\t- 512 byte block size" << endl;

        cout << "Disk Initialization Complete" << endl;
    }

    // Reads from storate
    void read() {

    }

    // Write to storage
    void write(string filename) {

        ifstream f(R"(D:\Documents\University-Assignments\CS4348 - Operating Systems\Proj3\)" + filename,
                   ios::binary | ios::in);

        string binary;
        char c;
        while (f.get(c)) {
            binary = binary + c;
        }
        f.close();

        cout << "binary: " << binary << endl;
        cout << binary.size() << endl;
        cout << filename << endl;

        _write(binary.size(), binary, filename);

    }

    string get_block(int block_index) {
        bitset<4096> free_space_table = memory[block_index];
        return "Block " + to_string(block_index) + ":\n" + free_space_table.to_string();
    }

    string print_file_allocation_table() {
        // Need to print in a table like fashion: iterate over bits
        bitset<256> out;
        bitset<4096> file_allocation_table = memory[0];

        int calc = 0;
        for (int i = 0; i < 4096; i++) {
            if (file_allocation_table[i] == 1) {
                calc++;
            } else {
                calc = 0;
            }

            if (calc == (8 - 1)) {
                out[255 - floor(i / (8 - 1))] = true;
                calc = 0;
            }
        }
        return "File Allocation Table:\n" + out.to_string();
    }

    string print_free_space_table() {
        bitset<256> out;
        bitset<4096> free_space_table = memory[1];

        int calc = 0;
        for (int i = 0; i < 4096; i++) {
            if (free_space_table[i] == 1) {
                calc++;
            } else {
                calc = 0;
            }

            if (calc == (8 - 1)) {
                out[255 - floor(i / (8 - 1))] = true;
                calc = 0;
            }
        }
        return "Free Space Table:\n" + out.to_string();
    }

};


int main() {

    cout << "Operating Systems is Boring" << endl;

    ifstream f("D:/Documents/University-Assignments/CS4348 - Operating Systems/Proj3/t.txt", ios::binary | ios::in);

    _disk disk(0);


    string selection;
    bool running = true;
    while (running) {

        string response;

        /* Clear linux console here */
//        system("clear");

        cout << "1) Display a file" << endl;
        cout << "2) Display the file table" << endl;
        cout << "3) Display the free space bitmap" << endl;
        cout << "4) Display a disk block" << endl;
        cout << "5) Copy a file from the simulation to a file on the real system" << endl;
        cout << "6) Copy a file from the real system to the simulation" << endl;
        cout << "7) Delete a file" << endl;
        cout << "8) Exit" << endl << endl;

        cout << selection << endl << endl;

        cout << ">> ";
        getline(cin, response);

        std::istringstream iss(response);
        std::vector<std::string> tokens((std::istream_iterator<std::string>(iss)),
                                        std::istream_iterator<std::string>());

        char *buffer;
        switch (strtol(tokens.at(0).c_str(), &buffer, 10)) {
            case 1:
                cout << "Called 1!" << endl;
                break;
            case 2:
                cout << "Called 2!" << endl;
                selection = disk.print_file_allocation_table();
                break;
            case 3:
                cout << "Called 3!" << endl;
                selection = disk.print_free_space_table();
                break;
            case 4:
                cout << "Called 4!" << endl;
                selection = disk.get_block(strtol(tokens.at(1).c_str(), &buffer, 10));
                break;
            case 5:
                cout << "Called 5!" << endl;
                break;
            case 6:
                cout << "Called 6!" << endl;
                disk.write(tokens.at(1));
                break;
            case 7:
                cout << "Called 7!" << endl;
                break;
            case 8:
                cout << "Called 8!" << endl;
                running = false;
                break;
            default: ;
        }

    }

    return 0;
}

