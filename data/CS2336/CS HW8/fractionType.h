#ifndef FRACTIONTYPE_H
#define FRACTIONTYPE_H

#include <iostream>
#include <string>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

using namespace std;

class fractionType
{
    public:
        fractionType(double,double);
        fractionType operator+(fractionType);
        fractionType operator-(fractionType);
        fractionType operator*(fractionType);
        fractionType operator/(fractionType);
        fractionType operator=(fractionType);
        friend istream& operator>>(istream&,fractionType&);
        friend ostream& operator<<(ostream&,fractionType&);
        int a;
        int b;


};

#endif // FRACTIONTYPE_H
