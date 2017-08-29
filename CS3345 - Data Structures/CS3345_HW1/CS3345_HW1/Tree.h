#pragma once
#include "Node.h"
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <iostream>

class Tree
{
public:
	// Constructors
	Tree();
	~Tree();
	
	//Get & Set Methods
	Node* getRoot();
	void setRoot(Node*);

	// Tree Functions
	void insert(Node*);
	void heapify(Node*);
	void swap(Node* a, Node* b);

	// Utility Functions
	void print(Node*);
	bool find(int[],int);

private:
	Node* root;
};

