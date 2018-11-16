#include "node.h"
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <queue>

using namespace std;

node::node(node* obj) //Node Copy Constructor
{
    coef = obj->getCoef(); //Copy Coefficient
    exp = obj->getExp(); //Copy Exponent
    left = obj->getLeft(); //Copy Left Node Pointer
    right = obj->getRight(); //Copy Right Node Pointer
    hasX = obj->getX(); //Copy HasX Boolean
}

string node::toString() //Returns the String of the Function
{
    stringstream ss;

    if(hasX == true) //If the function has an X
    {
        if(exp > 0) //If the exponent is not 0
        {
            ss << coef << "x^" << exp;

        }
        else //The exponent is 0
        {
            ss << coef << "x";
        }
    }
    else //Constant Value
    {
        ss << coef;
    }

    string tmp = ss.str();
    return tmp;
}

