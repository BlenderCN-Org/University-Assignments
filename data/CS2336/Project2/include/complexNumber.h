#ifndef COMPLEXNUMBER_H
#define COMPLEXNUMBER_H

#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

using namespace std;

class complexNumber
{
    public:
        complexNumber();
        complexNumber(string); //Value
        complexNumber(double,double); //Real + Imag
        complexNumber(double,double,string); //Real + Imag + String
        complexNumber(double,double,string,double); //Real + Imag + String + Denom
        complexNumber operator+(complexNumber); // +
        complexNumber operator-(complexNumber); // -
        complexNumber operator*(complexNumber);// *
        complexNumber operator/(complexNumber);// /
        bool operator==(complexNumber); // Checks Equal
        bool operator>(complexNumber); // MAGNITUDE
        bool operator<(complexNumber); // MAGNITUDE
        bool operator!=(complexNumber); // Checks NotEqual
        friend ostream& operator<<(ostream&,const complexNumber&);
        friend istream& operator>>(istream&,complexNumber&);
        //ACCESSORS AND MUTATORS::
        string getVal();
        void setVal(string);
        string val = "";
        double getReal();
        void setReal(double);
        double real = 0;
        double getImag();
        void setImag(double);
        double imag = 0;
        double getDenom();
        void setDenom(double);
        double denom = 0;

    protected:

    private:
};

#endif // COMPLEXNUMBER_H
