#ifndef RECTANGLE_H_INCLUDED
#define RECTANGLE_H_INCLUDED
#include "BasicShape.h"

class Rectangle : public BasicShape
{
    public:
        Rectangle(double thisWidth, double thisLength){width = thisWidth; length = thisLength;}
        double getWidth(){return width;}
        double getLength(){return length;}
        void setWidth(double amt){width = amt;}
        void setLength(double amt){length = amt;}
        double calcArea(){area = (getWidth() * getLength()); return area;}


    private:
        double width;
        double length;
};


#endif // RECTANGLE_H_INCLUDED
