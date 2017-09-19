// Homework2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "BinarySearchTree.h"

int main()
{
	BinarySearchTree* bst = new BinarySearchTree();
	bst->insert(7);
	bst->insert(9);
	bst->insert(4);
	bst->insert(1);

	bst->printTree(bst->getHead());

    return 0;
}


bool verifyHeight(BinarySearchTree bst) {
	return false;
}

bool verifyAVLTree(BinarySearchTree bst) {
	return false;
}

