#ifndef GRAPH_H
#define GRAPH_H

#include <iostream>
#include "string"
#include "linkedlist.h"
#include <stdlib.h>
#include <vector>
#include "node.h"

class Graph
{
    public:

        //Default Constructor
        Graph(){};

        //Copy Constructor
        virtual ~Graph() {};

        //The Graph
        std::vector<LinkedList> Keys;

        //Inserts a Point into the Graph
        void insert(std::string);

        //Returns True if the graph contains the key
        bool findKey(std::string);

        //Returns the Key Position
        int findKeyPos(std::string);

        //Finds the Key of a Coordinate
        bool findKeyAndCoor(std::string,std::string);

        //Traverses the Graph VIA Node Input
        void traversal(Node*);

    protected:

    private:
};

#endif // GRAPH_H
