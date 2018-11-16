#ifndef BASICSHAPE_H
#define BASICSHAPE_H
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>



class BasicShape
{
    public:
        BasicShape();
        virtual ~BasicShape() = 0;
        double getArea();
        virtual double calcArea() = 0;

    protected:
        double area;

    private:
};

#endif // BASICSHAPE_H
