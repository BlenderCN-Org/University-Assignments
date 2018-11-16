#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

#include "lineType.h"




using namespace std;

int main()
{
    string in;

    lineType x;
    cout << "Enter a function(1) in the standard:: ax + by = c" << "\n";
    getline(cin,in);
    x.f = in;
    cout << "Line X:: " << x.f << "\n";

    lineType y;
    cout << "Enter a function(2) in the standard:: ax + by = c" << "\n";
    getline(cin,in);
    y.f = in;
    cout << "Line Y:: " << y.f << "\n";

    x.getVal(x.f);
    x.checkSlope(x);

    y.getVal(y.f);
    y.checkSlope(y);

    x.checkEqual(x,y);
    x.checkPara(x,y);
    x.checkPerp(x,y);
    x.checkInter(x,y);


}




