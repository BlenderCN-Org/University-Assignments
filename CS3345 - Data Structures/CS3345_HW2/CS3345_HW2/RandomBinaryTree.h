#pragma once
#include "AVLNode.h"
class RandomBinaryTree
{
public:
	// Constructors / Destructors
	RandomBinaryTree();
	~RandomBinaryTree();

	// Random Tree Creation
	void createTree();

	// Verification functions
	bool verifyBST();
	bool verifyBST(AVLNode*,int,int);
	bool verifyAVL();
	bool verifyAVL(AVLNode*);

	// Get/Set variable functions
	AVLNode* getHead();
	void setHead(AVLNode*);	

	// Recursive Tree Insertion functions
	void insert(int);
	AVLNode* insert(int, AVLNode*);

	// AVL Helper Functions
	int height(AVLNode*);
	int getBalance(AVLNode*);
	int greaterHeight(int, int);	

private:
	// 'Head' or 'Root' ptr node
	AVLNode* head;

};

