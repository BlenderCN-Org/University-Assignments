#include "binarysearchtree.h"
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <queue>



void binarysearchtree::insert(node* cur)
{
    node* tmp = root;
    while(true)
    {
        if(root == nullptr) //The root is null
        {
          //cout << "Set Root:: " << cur->toString() << endl;
          setRoot(cur);
          //cout << "ROOT MA IN INSERT:: " << cur << endl;
          //cout << root->getCoef() << endl;
          break;
        }
        else if(cur->getExp() < tmp->getExp() && tmp->getLeft() == nullptr) //If the node is less than and the left pointer is null
        {
            //cout << "Set Left of Something:: " << cur->toString() << endl;
            tmp->setLeft(cur);
            break;
        }
        else if(cur->getExp() > tmp->getExp() && tmp->getRight() == nullptr) //If the node is greater than and the right pointer is null
        {
            //cout << "Set Right of Something:: " << cur->toString() << endl;
            tmp->setRight(cur);
            break;
        }
        if(tmp->getLeft() != nullptr) //Sets the left TMP node
        {
            //cout << "MOVED LEFT:: " << endl;
             tmp = tmp->left;
        }
        if(tmp->getRight() != nullptr) //Setst the right TMP node
        {
            //cout << "MOVED RIGHT:: " << endl;
            tmp = tmp->right;
        }
    }
}

queue<node> binarysearchtree::printTree()
{
    queue<node> n; //Creates a Node Queue
    string tmp = "IN"; //IN for InOrder
    traversal(tmp,root,n); //Traverses the Tree and puts the values into the Queue, then returns the Queue
    return n;
}

void destr(node* cur)
{
    if(cur) //If cur is not null
    {
        destr(cur->getLeft()); //Search the Left
        destr(cur->getRight()); //Search the Right
        delete cur; //Deletes the Node
    }
}

void binarysearchtree::traversal(string t,node* cur,queue<node>& n)
{
    if(t == "PRE") //PreOrder
    {
        if(cur) //If cur isnt null
        {
            n.push(cur); //Pushes the node
            traversal(t,cur->left,n); //Search the Left
            traversal(t,cur->right,n); //Search the Right
        }
    }

    if(t == "IN") //InOrder
    {
        if(cur) //If cur isnt null
        {
            traversal(t,cur->getRight(),n); //Search the Right
            n.push(cur); //Pushes the node
            traversal(t,cur->getLeft(),n); //Search the Left
        }
    }

    if(t == "POST") //PostOrder
    {
        if(cur) //If cur isnt null
        {
            traversal(t,cur->left,n); //Search the Left
            traversal(t,cur->right,n); //Search the Right
            n.push(cur); //Pushes the node

        }
    }
}

bool binarysearchtree::search(node* cur,node* r)
{
    if(r) //If the R node is not null
    {
        search(cur,r->getLeft()); //InOrder Search Left
        if(cur == r) //If the node are there...
        {
            return true;
        }
        search(cur,r->getRight()); //InOrder Search Right
    }
    return false;
}


