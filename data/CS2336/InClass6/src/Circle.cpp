#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "Circle.h"




using namespace std;

Circle::Circle()
{
    //ctor
}

Circle::~Circle()
{
    //dtor
}

Circle::Circle(long x, long y, double r)
{
    centerX = x;
    centerY = y;
    radius = r;

    calcArea();
}
long Circle:: getCenterX()
{
    return centerX;
}

long Circle:: getCenterY()
{
    return centerY;
}

void Circle::setCenterX(long n)
{
    centerX = n;
}

void Circle::setCenterY(long n)
{
    centerY = n;
}

void Circle::setRadius(double r)
{
    radius = r;

    calcArea();
}

double Circle::calcArea()
{
    area = 3.14159 * radius * radius;
    return area;
}
