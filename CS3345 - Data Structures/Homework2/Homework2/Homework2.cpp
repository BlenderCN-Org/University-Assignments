// Homework2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "BinarySearchTree.h"

int main()
{
	BinarySearchTree* bst = new BinarySearchTree();
	bst->insert(2);
	bst->insert(5);
	bst->insert(7);

	bst->printTree(bst->getHead());

    return 0;
}


bool verifyHeight(BinarySearchTree bst) {
	return false;
}

bool verifyAVLTree(BinarySearchTree bst) {
	return false;
}

