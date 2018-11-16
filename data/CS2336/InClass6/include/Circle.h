#ifndef CIRCLE_H
#define CIRCLE_H
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <BasicShape.h>


class Circle : public BasicShape
{
    public:
        Circle();
        virtual ~Circle();
        Circle(long,long,double);
        long getCenterX();
        long getCenterY();
        void setCenterX(long);
        void setCenterY(long);
        void setRadius(double);
        double calcArea();

    protected:

    private:
        long centerX;
        long centerY;
        double radius;
};

#endif // CIRCLE_H
