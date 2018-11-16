#ifndef BINARYSEARCHTREE_H
#define BINARYSEARCHTREE_H

#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <queue>

#include "node.h"

/*
| 3x^2 + 2x + 1 dx
1|4 x^-2 +3x+4 dx
-2|2 x^3 – 4x dx

*/

class binarysearchtree
{
    public:
        binarysearchtree(){root = nullptr;} //BinarySearchTree Default Constructor
        node* root = nullptr; //Node "Root" Pointer
        void destr(node* cur); //Destroys the BST
        void setRoot(node* x) {root=x;} //Sets the root of BST
        node* getRoot() {return root;} //Gets the root of BST
        void insert(node*); //Insert a node into the BST
        bool search(node*,node*); //Searches for a node in the BST, returns true
        void traversal(string,node* cur,queue<node>&); //Traverses the Tree
        queue<node> printTree(); //Prints the Tree

    protected:

    private:
};

#endif // BINARYSEARCHTREE_H
