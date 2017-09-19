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
	int balance(AVLNode*);
	int greaterHeight(int, int);

	// AVL Rotation Functions
	AVLNode* rotateRight(AVLNode* cur);
	AVLNode* rotateLeft(AVLNode* cur);
	AVLNode* rotateDoubleLeftRight(AVLNode* cur);
	AVLNode* rotateDoubleRightLeft(AVLNode* cur);
private:
	AVLNode* head;
	int maxHeight;
	int numNodes;
};

