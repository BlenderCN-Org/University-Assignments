#pragma once
#include "AVLNode.h"
#include <iostream>
class BinarySearchTree
{
public:
	// Constructors / Destructors
	BinarySearchTree();
	~BinarySearchTree();

	// Get / Set Functions
	AVLNode* getHead();
	void setHead(AVLNode*);

	// Recursive Tree Insertion functions
	void insert(int,std::string);
	AVLNode* insert(int, AVLNode*, std::string);

	// AVL Helper Functions
	int height(AVLNode*);
	int getBalance(AVLNode*);
	int greaterHeight(int, int);

	// AVL Rotation Functions
	AVLNode* rotateRight(AVLNode* cur);
	AVLNode* rotateLeft(AVLNode* cur);

	// Prints the Tree in an 'IN-ORDER' Traversal
	void print(AVLNode*);

private:
	// 'Head' or 'Root' ptr node
	AVLNode* head;	
};

