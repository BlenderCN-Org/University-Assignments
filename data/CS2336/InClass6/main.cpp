#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

#include "BasicShape.h"
#include "Circle.h"
#include "Rectangle.h"

using namespace std;

int main()
{
    Circle x(4,6,9.0);
    Rectangle y(7,8);

    cout << "getCenterX():: " << x.getCenterX() << "\n";
    cout << "getCenterY():: " << x.getCenterY() << "\n";
    x.setCenterX(6);
    x.setCenterY(9);
    x.setRadius(3.0);
    cout << "Circle .getArea():: " << x.getArea() << "\n";;

    cout << "getLength():: " << y.getLength() << "\n";
    cout << "getWidth():: " << y.getWidth() << "\n";
    y.setLength(2);
    y.setWidth(5);
    y.calcArea();
    cout << "Rectangle .getArea():: " << y.getArea() << "\n";
}
