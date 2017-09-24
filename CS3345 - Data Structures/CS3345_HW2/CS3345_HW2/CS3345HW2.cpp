// CS3345HW2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "BinarySearchTree.h"
#include "RandomBinaryTree.h"
#include <stdio.h>
#include <stdlib.h>
#include <iostream>


using std::cout;
using std::endl;
using std::cin;
using std::string;

int main()
{
	/* REGULAR ASSIGNMENT */	
	
	BinarySearchTree* bst = new BinarySearchTree();
	freopen("book_data.txt", "r", stdin);

	int tmpISBN;
	char tmpNAME[64];

	while (scanf("%i %[^\n]", &tmpISBN, &tmpNAME) == 2) {
		bst->insert(tmpISBN, tmpNAME);		
	}	

	bst->print(bst->getHead());
	cout << endl; cout << endl; cout << endl;
	

	/* EXTRA CREDIT : RANDOM BINARY TREE */
	
	RandomBinaryTree* rbt = new RandomBinaryTree();
	rbt->createTree();
	rbt->print(rbt->getHead());	cout << endl; cout << endl;

	if (rbt->verifyBST())
		cout << "IS A BST" << endl;
	else
		cout << "NOT A BST" << endl;

	if (rbt->verifyAVL()) 
		cout << "IS A AVL" << endl; 
	else
		cout << "NOT A AVL" << endl;	

    return 0;
}

