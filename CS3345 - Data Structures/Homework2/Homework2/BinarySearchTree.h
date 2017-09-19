#pragma once
#include "Node.h"
#include <iostream>

class BinarySearchTree
{
public:
	BinarySearchTree();
	~BinarySearchTree();

	int maxHeight;

	void insert(int);
	void insert(Node*);
	void adjustHeights(int);
	
	Node* getHead();
	void setHead(Node*);

	void printTree(Node*);

private:
	Node* head;
};

