#ifndef LARGEINTEGERS_H
#define LARGEINTEGERS_H
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



class largeIntegers
{
    public:
        largeIntegers(string arra);
        void setArr(string arra);
        largeIntegers operator+(largeIntegers);
        largeIntegers operator-(largeIntegers);
        largeIntegers operator*(largeIntegers);
        largeIntegers operator/(largeIntegers);
        largeIntegers operator>(largeIntegers);
        largeIntegers operator<(largeIntegers);
        largeIntegers operator=(largeIntegers);
        friend istream& operator>>(istream&,largeIntegers&);
        friend ostream& operator<<(ostream&,largeIntegers&);
        int length;
        int* arr;
        int* comp;
        string cNumber;
        bool isOver = false;
        bool isLess = false;


    protected:

    private:
};

#endif // LARGEINTEGERS_H
