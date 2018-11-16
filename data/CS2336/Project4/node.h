#ifndef NODE_H
#define NODE_H
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

class node
{
    public:
        node(){coef = 0; exp = 0;} //Node Default Constructor
        node(double x,double y){coef = x; exp = y;} //Node Overloaded Default Constructor
        node(node* obj); //Node Copy Constructor
        double coef; //Coefficient Value
        double exp; //Exponent Value
        bool hasX = false; //Does the Value have an X?
        node* left = nullptr; //Left Pointer Node
        node* right = nullptr; //Right Pointer Node

        void setX(bool x){hasX = x;} //Sets the Boolean Value
        bool getX(){return hasX;} //Returns the Boolean Value

        void setCoef(double x) {coef = x;} //Set the Coefficient Value
        double getCoef() {return coef;} //Returns the Coefficient Value

        void setExp(double x) {exp =  x;} //Sets the Exponent Value
        double getExp() {return exp;} //Returns the Coefficient Value

        void setLeft(node* x) {left = x;} //Sets the Left Node Pointer
        node* getLeft(){return left;} //Returns the Left Node Pointer

        void setRight(node*x) {right = x;} //Sets the Right Node Pointer
        node* getRight(){return right;} //Returns the Right Node Pointer

        string toString(); //Returns the String of the Function

    protected:

    private:
};

#endif // NODE_H
