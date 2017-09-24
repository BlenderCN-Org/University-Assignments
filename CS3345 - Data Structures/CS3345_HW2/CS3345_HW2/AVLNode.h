#pragma once
#include "Book.h"

class AVLNode
{
public:
	// Constructors / Destructors
	AVLNode();
	AVLNode(int);
	AVLNode(int, Book*);
	~AVLNode();
	
	// Get / Set Functions
	AVLNode* getRight();	
	AVLNode* getLeft();	
	int getHeight();
	int getKey();
	void setRight(AVLNode*);
	void setLeft(AVLNode*);	
	void setKey(int);
	void setHeight(int);
	void setBook(int, std::string);

private:
	// Where the node is. ISBN Number
	int key;

	// Node's value
	Book* book;

	// Node's current height
	int height;

	// Left node pointer
	AVLNode* left;

	// Right node pointer
	AVLNode* right;

};

