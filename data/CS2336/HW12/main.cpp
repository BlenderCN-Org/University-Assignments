#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <functional>

#include "node.h"
#include "binarysearchtree.h"


using namespace std;

int main()
{
    BinarySearchTree t1;
    BinarySearchTree t2;
    BinarySearchTree t3;

    node* pg = new node();

    node* a = new node(12);
    node* b = new node(4);
    node* c = new node(14);
    node* d = new node(16);
    node* e = new node(2);
    node* f = new node(8);
    node* g = new node(6);
    node* h = new node(10);

    t1.insert(a);
    t1.insert(b);
    t1.insert(c);
    t1.insert(d);
    t1.insert(e);
    t1.insert(f);
    t1.insert(g);
    t1.insert(h);


    /*
    t1.traversal("POST",t1.root,t2);
    cout << endl;
    cout << "LEAVES:: " << t1.getLeaves(t1.root) + 1 << endl;
    cout << "HEIGHT:: " << t1.getHeight(t1.root) << endl;

    t2.traversal("PRE",t2.root,t3);
    cout << endl;
    cout << "LEAVES:: " << t2.getLeaves(t2.root) + 1 << endl;
    cout << "HEIGHT:: " << t2.getHeight(t2.root) << endl;

    t3.traversal("IN",t3.root);
    cout << endl;
    cout << "LEAVES:: " << t3.getLeaves(t3.root) + 1 << endl;
    cout << "HEIGHT:: " << t3.getHeight(t3.root) << endl;
    */






}
