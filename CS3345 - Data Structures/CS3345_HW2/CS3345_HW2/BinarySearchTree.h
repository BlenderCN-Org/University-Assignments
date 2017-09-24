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

	void insert(int,std::string);
	AVLNode* insert(int, AVLNode*, std::string);
	void print(AVLNode*);

	// AVL Helper Functions
	int height(AVLNode*);
	int getBalance(AVLNode*);
	int greaterHeight(int, int);

	// AVL Rotation Functions
	AVLNode* rotateRight(AVLNode* cur);
	AVLNode* rotateLeft(AVLNode* cur);

private:
	AVLNode* head;
	int maxHeight;
	int numNodes;
};

