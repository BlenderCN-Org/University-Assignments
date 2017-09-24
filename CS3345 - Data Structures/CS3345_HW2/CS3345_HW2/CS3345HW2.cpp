// CS3345HW2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "BinarySearchTree.h"
#include "RandomBinaryTree.h"

using std::cout;
using std::endl;

int main()
{
	/* REGULAR ASSIGNMENT */
	
	BinarySearchTree* bst = new BinarySearchTree();
	bst->insert(12);
	bst->insert(4);
	bst->insert(7);		

	cout << endl; cout << endl; cout << endl;

	/* EXTRA CREDIT : RANDOM BINARY TREE */
	
	RandomBinaryTree* rbt = new RandomBinaryTree();
	rbt->createTree();
	rbt->print(rbt->getHead());	cout << endl; cout << endl;

	if (rbt->verifyBST(rbt->getHead()))
		cout << "IS A BST" << endl;
	else
		cout << "NOT A BST" << endl;

	if (rbt->verifyAVL(rbt->getHead())) {
		cout << "IS A AVL" << endl;
	}
	else
		cout << "NOT A AVL" << endl;


    return 0;
}

