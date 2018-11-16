#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "complexNumber.h"




using namespace std;

complexNumber::complexNumber()
{

}
complexNumber::complexNumber(string l) // Constructor via String
{
    val = l;

    if((getVal().find("+") != string::npos || getVal().find("-") != string::npos) && getVal().find("i") != string::npos) // Real + Imag
    {
        if(getVal().find("+") != string::npos)
        {
            real = atof(getVal().substr(0,getVal().find("+")).c_str());
            imag = atof(getVal().substr(getVal().find("+"),getVal().find("i")).c_str());
        }
        else //GOTTA GET THOSE NEGATIVES
        {
            if(getVal().find("-") == 0)
            {
                val = val.substr(1);
                real = atof(getVal().substr(0,getVal().find("-")).c_str());
                imag = atof(getVal().substr(getVal().find("-"),getVal().find("i")).c_str());
                real = real*-1;
            }
            else
            {
                real = atof(getVal().substr(0,getVal().find("-")).c_str());
                imag = atof(getVal().substr(getVal().find("-"),getVal().find("i")).c_str());
            }
        }
    }
    else if(getVal().find("i") == string::npos) // Real
    {
        real = atof(getVal().c_str());
    }
    else// Imag
    {
        imag = atof(getVal().substr(0,getVal().find("i")).c_str());
    }
}
complexNumber::complexNumber(double r, double i)  // Constructor via real and imag
{
    real = r;
    imag = i;
}
complexNumber::complexNumber(double r, double i,string l) // Constructor via real and imag and string
{
    real = r;
    imag = i;
    val = l;
}
complexNumber::complexNumber(double r,double i,string l,double d) // Constructor via real and imag and string and the denominator
{
    real = r;
    imag = i;
    val = l;
    denom = d;
}
complexNumber complexNumber:: operator+(complexNumber x) // +
{
    double r2 = getReal() + x.getReal();
    double i2 = getImag() + x.getImag();
    stringstream ss;
    ss << setprecision(2) << fixed << r2 << "+" << i2 << "i";

    complexNumber tmp(r2,i2,ss.str());

    return tmp;
}
complexNumber complexNumber:: operator-(complexNumber x) // -
{
    double r2 = getReal() - x.getReal();
    double i2 = getImag() - x.getImag();
    stringstream ss;
    ss <<  setprecision(2) << fixed <<r2 << "+" << i2 << "i";


    complexNumber tmp(r2,i2,ss.str());

    return tmp;
}
complexNumber complexNumber:: operator*(complexNumber x) // *
{
    double r2 = (getReal()*x.getReal()) + ((getImag()*x.getImag())*-1); // +
    double i2 = (getReal()*x.getImag()) + (getImag()*x.getReal());
    stringstream ss;
    ss << setprecision(2) << fixed << r2 << "+" << i2 << "i";

    complexNumber tmp(r2,i2,ss.str());

    return tmp;
}
complexNumber complexNumber:: operator/(complexNumber x) // /
{
    double r2 = (getReal()*x.getReal()) + (getImag()*(-1*x.getImag()))*-1;
    double i2 = (getReal()*(-1*x.getImag())) + (getImag()*x.getReal()); // /
    double den = (x.getReal()*x.getReal()) + (x.getImag()*(-1*x.getImag()))*-1;
    stringstream ss;
    ss << setprecision(2) << fixed << r2 << "+" << i2 << "i" << "/" << den;

    //string rr = r2.str();
    //val = r2 + " " + i2 + "/" + denom;

    complexNumber tmp(r2,i2,ss.str(),den);

    return tmp;
}
bool complexNumber::operator==(complexNumber x) // ==, compares if they are equal
{
    if((getReal() == x.getReal()) && (getImag() == x.getImag()))
    {
        return true;
    }
    else
    {
       return false;
    }
}
bool complexNumber::operator>(complexNumber x) // >, compares if x > y
{
    double fmag = sqrt(pow(getReal(),2)+pow(getImag(),2));
    double smag = sqrt(pow(x.getReal(),2)+pow(x.getImag(),2));

    if(fmag > smag)
    {
        return true;
    }
    else
    {
        return false;
    }

    return true;
}
bool complexNumber::operator<(complexNumber x) // <, compares if x < y
{
    double fmag = sqrt(pow(getReal(),2)+pow(getImag(),2));
    double smag = sqrt(pow(x.getReal(),2)+pow(x.getImag(),2));

    if(fmag < smag)
    {
        return true;
    }
    else
    {
        return false;
    }

    return true;
}
bool complexNumber::operator!=(complexNumber x) // !=, compares if x != y
{
    if((getReal() != x.getReal()) || (getImag() != x.getImag()))
    {
        return true;
    }
    else
    {
       return false;
    }
}
ostream& operator<<(ostream& OS,const complexNumber& x)// output overloaded operator
{
    if(x.denom != 0)
    {
        OS << setprecision(2) << fixed << x.val;
    }
    else
    {
        OS << setprecision(2) << fixed << x.val;
    }

    return OS;


}
istream& operator>>(istream& t,complexNumber& x) // input overloaded operator
{
    string tmp;
    t >> tmp;
    x.setVal(tmp);

    if((tmp.find("+") != string::npos || tmp.find("-") != string::npos) && tmp.find("i") != string::npos) // Real + Imag
    {
        if(tmp.find("+") != string::npos)
        {
            x.setReal(atof(tmp.substr(0,tmp.find("+")).c_str()));
            x.setImag(atof(tmp.substr(tmp.find("+"),tmp.find("i")).c_str()));
        }
        else
        {
            if(x.getVal().find("-") == 0)
            {
                tmp = tmp.substr(1);
                x.real = atof(tmp.substr(0,tmp.find("-")).c_str());
                x.imag = atof(tmp.substr(tmp.find("-"),tmp.find("i")).c_str());
                x.real = x.real*-1;
            }
            else
            {
                x.real = atof(tmp.substr(0,tmp.find("-")).c_str());
                x.imag = atof(tmp.substr(tmp.find("-"),tmp.find("i")).c_str());
            }
        }
    }
    else if(tmp.find("i") == string::npos) // Real
    {
        x.real = atof(tmp.c_str());
    }
    else// Imag
    {
        x.imag = atof(tmp.substr(0,tmp.find("i")).c_str());
    }

    return t;

}

// Accessor and Mutator Methods for getting and setting the inner variables
void complexNumber::setVal(string x)
{
    val = x;
}
string complexNumber::getVal()
{
    return val;
}
double complexNumber::getReal()
{
    return real;
}
void complexNumber::setReal(double x)
{
    real = x;
}
double complexNumber::getImag()
{
    return imag;
}
void complexNumber::setImag(double x)
{
    imag = x;
}
double complexNumber::getDenom()
{
    return denom;
}
void complexNumber::setDenom(double x)
{
    denom = x;
}
