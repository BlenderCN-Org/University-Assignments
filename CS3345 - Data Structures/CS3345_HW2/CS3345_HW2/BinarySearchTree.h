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
	void print(AVLNode*);
	bool verifyAVLTree();

	void fixAVLTree();

private:
	AVLNode* head;
	int maxHeight;
	int numNodes;
};

