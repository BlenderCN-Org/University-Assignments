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

	// These next few lines redirect input from STDIN to the file 'book_data.txt', read data from the file 'book_data.txt' and create a binary search tree with it
	// Also prints the tree with an 'IN-ORDER' traversal after each insertion, giving the ISBN and the Node*'s height

	cout << "::REGULAR ASSIGNMENT::" << endl << endl;

	freopen("book_data.txt", "r", stdin);
	int tmpISBN;
	char tmpNAME[1024];

	while (scanf("%i %[^\n]", &tmpISBN, &tmpNAME) == 2) {
		bst->insert(tmpISBN, tmpNAME);		
	}		

	cout << endl; cout << endl; cout << endl;


	/* EXTRA CREDIT | RANDOM BINARY TREE */	

	RandomBinaryTree* rbt = new RandomBinaryTree();

	cout << "::EXTRA CREDIT | RANDOM BINARY TREE::" << endl << endl;

	// Creates a random binary tree, with nodes 'n' 1 <= n <= 20, and keys 'k' 0 <= k <= 99	
	rbt->createTree();

	// Calls VerifyBST(), and prints 'IS A BST' if true, 'NOT A BST' if false
	if (rbt->verifyBST())
		cout << "Random Binary Tree 'rbt' IS a BST" << endl;
	else
		cout << "Random Binary Tree 'rbt' IS NOT a BST" << endl;

	// Calls VarifyAVL(), and prints 'IS A AVL' if true, 'NOT A AVL' if false
	if (rbt->verifyAVL())
		cout << "Random Binary Tree 'rbt' IS a AVL Tree" << endl << endl;
	else
		cout << "Random Binary Tree 'rbt' IS NOT a AVL Tree" << endl << endl;

    return 0;
}

