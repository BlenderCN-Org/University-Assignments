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

	AVLNode* insert(AVLNode*,int,int);
	void print(AVLNode*);

	bool verifyAVLTree();

private:
	AVLNode* head;
	int maxHeight;
	int numNodes;
};

