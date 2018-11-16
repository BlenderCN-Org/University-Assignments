#include "node.h"
#include <string>
#include <stdlib.h>


Node::Node(std::string vertex)
{

    //There is only a point
    if(vertex.find(",")==std::string::npos){
        val = atof(vertex.c_str());

    }
    //There is a list of coordinates
    else{
        val = atof(vertex.substr(0,vertex.find(",")).c_str());
        weight = atof(vertex.substr(vertex.find(",")+1).c_str());

    }

}
