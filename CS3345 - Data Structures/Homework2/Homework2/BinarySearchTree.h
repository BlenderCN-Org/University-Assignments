#pragma once
#include "Node.h"
#include <iostream>

class BinarySearchTree
{
public:
	BinarySearchTree();
	~BinarySearchTree();

	void insert(int);
	void insert(Node*);
	Node* getHead();
	void setHead(Node*);

	void printTree(Node*);

private:
	Node* head;
};

