#ifndef INVALIDMONTH_H
#define INVALIDMONTH_H
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <exception>
using namespace std;

class invalidMonth
{
    public:
        invalidMonth(); // Default Constructor
        string what(); // Outprints the Error
        string message;

    protected:

    private:
};

#endif // INVALIDMONTH_H
