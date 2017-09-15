// CS3345HW2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "BinarySearchTree.h"

using std::cout;
using std::endl;

int main()
{
	BinarySearchTree* bst = new BinarySearchTree();
	bst->setHead(bst->insert(bst->getHead(),10,0));
	cout << bst->getHead()->getHeight() << endl;
	bst->insert(bst->getHead(), 12, 0);
	bst->insert(bst->getHead(), 7, 0);
	bst->insert(bst->getHead(), 5, 0);
	bst->insert(bst->getHead(), 9, 0);
	bst->insert(bst->getHead(), 1, 0);
	bst->print(bst->getHead());
	cout << endl;
    return 0;
}

