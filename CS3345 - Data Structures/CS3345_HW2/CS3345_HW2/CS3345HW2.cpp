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
	bst->insert(7);
	bst->insert(13);
	bst->insert(9);
	bst->insert(4);
	bst->insert(3);
	bst->insert(2);
	bst->print(bst->getHead());	
	cout << endl;
    return 0;
}

