#ifndef NODE_H
#define NODE_H

#include <string>
#include <iostream>


class Node
{
    public:
        //Default Constructor
        Node() {val = 0; weight = 0; next = nullptr;}

        //Another Constructor
        Node(std::string);

        //Destructor
        virtual ~Node(){};

        //Coordinate
        double val;

        //Weight of the Coordinate
        double weight;

        //Visited Boolean
        bool v = false;

        //Next Node Pointer
        Node* next = nullptr;

        //Returns the next node
        Node* getNext() {return next;}

        //Sets the Next Node
        void setNext(Node* cur) {next = cur;}

        //Returns the Coordinate
        double getVal() {return val;}

        //Sets the Coordinate
        void setVal(double d) {val = d;}

        //Returns the Weight
        double getWeight() {return weight;}

        //Sets the Weight
        void setWeight(double d) {weight = d;}

    protected:

    private:
};

#endif // NODE_H
