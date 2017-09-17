#pragma once
#include "AVLNode.h"
#include <iostream>
class BinarySearchTree
{
public:
	BinarySearchTree();
	~BinarySearchTree();

	AVLNode* getHead();
	void setHead(AVLNode*);

	void insert(int);
	AVLNode* insert(int, AVLNode*);
	void print(AVLNode*);

	// AVL Helper Functions
	int height(AVLNode*);
	int greaterHeight(int, int);

private:
	AVLNode* head;
	int maxHeight;
	int numNodes;
};

