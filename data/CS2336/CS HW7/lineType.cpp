#include "lineType.h"

#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>



/*
void getVal(string);
void inputLine(int,int,int);
void checkSlope(int,int,int);
void checkEqual(int,int,int,string);
void checkPara(int,int,int,string);
void checkPerp(int,int,int,string);
void checkInter(int,int,int,string);
*/



using namespace std;


void lineType::getVal(string f) //ax + by = c || y = (-ax + c)/(b)
{
    a = atof(f.substr(0,f.find("x")).c_str());
    b = atof(f.substr(f.find("y")-1,f.find("y")).c_str());
    c = atof(f.substr(f.find("=")+2).c_str());
}

void lineType::checkSlope(lineType x)
{
    if(x.a != 0 && x.b != 0)
    {
        slope = (-1*x.a)/x.b;
    }
    else if (a == 0)
    {
        cout << "The Line Is Horizontal" << "\n";
    }
    else if(b == 0)
    {
        cout << "The Line Is Vertical" << "\n";
    }


}

void lineType::checkEqual(lineType x,lineType y)
{
    if(x.a == y.a && x.b == y.b && x.c == y.c)
    {
        cout << "The Lines Are Equal" << "\n";
    }
    else
    {
        cout << "The Lines Are Not Equal" << "\n";
    }
}

bool lineType::setpara(lineType x)
{
    x.para = 1;
    return x.para;
}

void lineType::checkPara(lineType x,lineType y)
{
    if(x.slope == y.slope)
    {
        cout << "The Lines Are Parallel" << "\n";
        x.para = setpara(x);
        y.para = setpara(y);
    }
    else
    {
        cout << "The Lines Are Not Parallel" << "\n";
    }
}

void lineType::checkPerp(lineType x,lineType y)
{
    if((-1/x.slope) == y.slope || x.slope * y.slope == -1)
    {
        cout << "The Lines Are Perpendicular" << "\n";
    }
    else
    {
        cout << "The Lines Are Not Perpendicular" << "\n";
    }
}

void lineType::checkInter(lineType x,lineType y)
{
    if(x.slope != y.slope)
    {
        double c2 = y.c/y.b;
        double c1 = x.c/x.b;

        double xCoor = (c2-c1) / (x.slope/y.slope);
        double yCoor = (x.slope * xCoor) + c1;

        cout << "The Lines Intersect at (" << xCoor << "," << yCoor << ")" << "\n";
    }
    else
    {
        cout << "The Lines Do Not Intersect or The Lines Are Parallel or Invalid Input" << "\n";
    }
}


lineType::lineType()
{
    //ctor
}

lineType::~lineType()
{
    //dtor
}
