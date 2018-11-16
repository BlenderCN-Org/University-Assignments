#ifndef INVALIDDAY_H
#define INVALIDDAY_H
#include <iostream>
#include <exception>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
using namespace std;


class invalidDay : public exception
{
    public:
        invalidDay(); // Default constructor
        string what(); // Outprints the Error
        string message;

    protected:

    private:
};

#endif // INVALIDDAY_H
