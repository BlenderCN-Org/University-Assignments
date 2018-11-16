#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

using namespace std;

int main()
{
    fstream file ("data.txt", ios::binary); //5 Letter Forward Jump (A = F)


    string line;
    while(getline(file,line))
    {
        file.open("data.txt", ios::binary);

        int len = line.length();

        for(int i = 0; i < len-1; i++)
        {
            if(!(atoi(line.substr(i,i+1).c_str())) && line.at(i) )
            {
                char tmp;
                tmp = line.at(i);

                if(tmp+5 > 122)
                {
                    tmp +=5;
                    tmp -= 122;
                }
                else if(tmp+5 > 90)
                {
                    tmp = tmp+5;
                    tmp -= 90;
                }
                else
                {
                    tmp = tmp+5;
                }

                file.close();
                file.open("output.txt", ios::binary);
                file << tmp;
                file.close();
            }
            else
            {
                file.close();
                file.open("output.txt", ios::binary);
                file << line.substr(i,i+1);
                file.close();
            }

        }
        file.open("output.txt", ios::binary);
        file << "\n";
        file.close();
    }
}
