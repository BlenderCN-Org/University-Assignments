#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "fractionType.h"



using namespace std;

fractionType::fractionType(double t,double b)
{
    this.a = t;
    this.b = d;
}

fractionType::fractionType operator+(fractionType x)
{
    t = (this.a*x.b) + (this.b*x.a);
    b = (this.b*x.b);

    return fractionType tmp(t,b);
}
fractionType::fractionType operator-(fractionType x)
{
    t = (this.a*x.b) - (this.b*x.a);
    b = (this.b*x.b);

    return fractionType tmp(t,b);
}
fractionType::fractionType operator*(fractionType x)
{
    t = this.a * x.a;
    b = this.b*x.b;

    return fractionType tmp(t,b);
}
fractionType::fractionType operator/(fractionType x)
{
    t = (this.a*x.b);
    b = (this.b*x.a);

    return fractionType tmp(t,b);
}
fractionType::fractionType operator/(fractionType x)
{
    t = (this.a*x.b);
    b = (this.b*x.a);

    return fractionType tmp(t,b);
}
fractionType::fractionType operator=(fractionType x)
{
   this.a = x.a;
   this.b = x.b;
   return fractionType tmp(t,b);
}
friend fractionType:: istream& operator>>(istream& t,fractionType& x)
{
    string tmp;

    t >> tmp;
    x.a = aoti(tmp.substr(0,1).c_str());
    x.b = aoti(tmp.substr(2,3).c_str());
}
friend fractionType:: ostream& operator<<(ostream& t,fractionType& x)
{
    string tmp;

    t << x.a << "/" << x.b;
}

/*
void fractionType::inputNorm(string l) // |a|/|b| |+| |c|/|d|
{
    a = atoi(l.substr(0,1).c_str());
    cout << "a:: " << a << "\n";

    b = atoi(l.substr(2,3).c_str());
    cout << "b:: " << b << "\n";

    //op = l.substr(4,5);
    //cout << "op:: " << op << "\n";

    c = atoi(l.substr(6,7).c_str());
    cout << "c:: " << c << "\n";

    d = atoi(l.substr(8,9).c_str());
    cout << "d:: " << d << "\n";
}

void fractionType::solve(int a,int b,int c,int d,string l)
{
    if(l.find("+") != -1)
    {
       cout << setprecision(2) << fixed << (a*d + b*c) << "/" << (b*d) << "\n";
    }
    else if(l.find("-") != -1)
    {
        cout << setprecision(2) << fixed << (a*d - b*c) << "/" << (b*d) << "\n";
    }
    else if(l.find("*") != -1)
    {
        cout << setprecision(2) << fixed << (double)(a*c) << "/" << (b*d) << "\n";
    }
    else if(l.find("/") != -1 && c/d != 0)
    {
        cout << setprecision(2) << fixed << (double)(a*d) << "/" << (b*c) << "\n";
    }
}
*/
