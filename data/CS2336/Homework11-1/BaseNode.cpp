#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "BaseNode.h"




using namespace std;

BaseNode* BaseNode::getNext()
{
    return next;
}
string BaseNode::getString()
{
    return s;
}
