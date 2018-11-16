#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

#include "lineType.h"
#include "lineType.cpp"




using namespace std;

int main()
{
    lineType x;
    x.f = "2x + 4y = 6";
    cout << "Line X:: " << x.f << "\n";

    lineType y;
    y.f = "2x + 4y = 20";
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




