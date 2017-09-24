#include "stdafx.h"
#include <stdio.h>  
#include <stdlib.h> 
#include <math.h>
#include <ctime>
#include "RandomBinaryTree.h"

// Default Constructor, initializes all variables to 0 or nullptr
RandomBinaryTree::RandomBinaryTree() {
	head = nullptr;
}

// Virtual Destructor
RandomBinaryTree::~RandomBinaryTree() {
}

// Creates a random binary tree, with nodes 'tree_val' 1 <= tree_val <= 20, and keys 'node_val' 0 <= node_val <= 99
void RandomBinaryTree::createTree() {
	srand((int)time(0));

	int tree_val = round((rand() % 20) + 1);
	int node_val;
	for (int i = 0; i < tree_val; i++) {
		node_val = rand() % 100;		
		insert(node_val);
	}
}

// Verify's the Binary Tree Property by Recursively doing an 'IN-ORDER' traversal and checking the properties
bool RandomBinaryTree::verifyBST() {
	return verifyBST(getHead(), INT_MIN, INT_MAX);
}

// Main meat of the VerifyBST function, returns true if tree is BST and false if tree is not BST
bool RandomBinaryTree::verifyBST(AVLNode* cur, int l, int r) {
	if (cur == nullptr) {
		return true;
	}

	if (cur->getKey() < l || cur->getKey() > r) {
		return false;
	}
	return verifyBST(cur->getLeft(), l, cur->getKey()) && verifyBST(cur->getRight(), cur->getKey(), r);
}

// Verify's the AVL Tree Properties by Recursively doing an 'IN-ORDER' traversal and checking the balance at each node
bool RandomBinaryTree::verifyAVL() {
	if (verifyBST()) {
		return verifyAVL(getHead());
	}
	else {
		return false;
	}		
}

// Main meat of the VerifyAVL function, returns true if tree is AVL and false if tree is not AVL
bool RandomBinaryTree::verifyAVL(AVLNode* cur) {
	if (cur == nullptr) {
		return true;
	}
	if (abs(getBalance(cur)) >= 2 ) {
		return false;
	}
	return verifyAVL(cur->getLeft()) && verifyAVL(cur->getRight());
}

// Returns the Head Node*
AVLNode* RandomBinaryTree::getHead() {
	return head;
}

// Sets the Head Node* to Node* cur
void RandomBinaryTree::setHead(AVLNode* cur) {
	head = cur;
}

// Reassigns the head node after insertion to fix AVL rotations
void RandomBinaryTree::insert(int key) {
	setHead(insert(key, getHead()));
}

// Recursive Binary Tree Insertion
AVLNode* RandomBinaryTree::insert(int key, AVLNode* cur) {	
	int lor = rand() % 2;

	if (cur == nullptr) {
		cur = new AVLNode(key);
	}
	else if (lor == 0) {
		cur->setLeft(insert(key, cur->getLeft()));
	}
	else if (lor == 1) {
		cur->setRight(insert(key, cur->getRight()));
	}
	cur->setHeight(greaterHeight(height(cur->getLeft()), height(cur->getRight())) + 1);
	return cur;
}

// Returns if int x is greater than int y
int RandomBinaryTree::greaterHeight(int x, int y) {
	return (x > y) ? x : y;
}

// Returns the height of a given Node* cur
int RandomBinaryTree::height(AVLNode* cur) {
	return (cur == nullptr) ? -1 : cur->getHeight();
}

// Retunrs the balance of a given node
int RandomBinaryTree::getBalance(AVLNode* cur) {
	if (cur == nullptr) {
		return 0;
	}
	else {
		return height(cur->getLeft()) - height(cur->getRight());
	}
}
