#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "largeIntegers.h"

using namespace std;

int main()
{
    //int s1[10] = {1,2,5,7,8,9,4,3,2,1};
    //int s2[10] = {2,2,5,7,3,9,4,3,5,9};
                 //3515288680

    string h = "247";
    string u = "34";

    largeIntegers x(h);
    largeIntegers y(u);

    x+y;
    x-y;
    x<y;
    x>y;
    x=y;
}

