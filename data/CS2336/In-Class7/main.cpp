#include <iostream>
#include <fstream>
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

#include <queue.h>

using namespace std;

int main()
{
    string line1;
    string line2;

    Queue e;
    Queue r;

    fstream q1;
    q1.open("QUEUE_FILE_1");

    fstream q2;
    q2.open("QUEUE_FILE_2");

    getline(q1,line1);
    getline(q2,line2);

    while(line1 != "")
    {
        int tmp = line1.find(" ");
        char t = line1.substr(0,tmp)[0];
        e.enqueue(t);

        if(line1.find(" ") == string::npos)
            line1 = "";
        else
            line1.substr(tmp+1);
    }

    while(line2 != "")
    {
        int tmp = line2.find(" ");
       char t = line1.substr(0,tmp)[0];
        e.enqueue(t);

        if(line2.find(" ") == string::npos)
            line2 = "";
        else
            line2.substr(tmp+1);
    }
    bool jtr = (e==r);
    cout << jtr;
}
