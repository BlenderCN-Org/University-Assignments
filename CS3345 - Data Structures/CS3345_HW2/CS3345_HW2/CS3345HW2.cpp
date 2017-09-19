// CS3345HW2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "BinarySearchTree.h"

using std::cout;
using std::endl;

int main()
{
	BinarySearchTree* bst = new BinarySearchTree();
	bst->insert(12);
	bst->print(bst->getHead()); cout << endl;
	bst->insert(14);
	bst->print(bst->getHead()); cout << endl;
	bst->insert(16);	
	bst->print(bst->getHead());	cout << endl;
    return 0;
}

