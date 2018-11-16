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
        BaseNode(string x){s = x;};
        string s;
        BaseNode* next;

        BaseNode* getNext();
        string getString();
        void setNext(BaseNode* ptr) {next = ptr;}

    protected:


    private:
};

#endif // BASENODE_H
