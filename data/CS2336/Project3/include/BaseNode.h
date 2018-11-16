#ifndef BASENODE_H
#define BASENODE_H

#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

using namespace std;

class BaseNode
{
    public:
        BaseNode();
        BaseNode(string,float);
        virtual ~BaseNode() = 0;

        string getName();
        float getArea();

        void setArea(float);
        void setName(string);

    protected:
        string name;
        float area;

    private:
};

#endif // BASENODE_H
